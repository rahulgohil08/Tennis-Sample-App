package com.theworld.androidtemplatewithnavcomponents.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theworld.androidtemplatewithnavcomponents.R
import com.theworld.androidtemplatewithnavcomponents.adapters.StringerAdapter
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.DeleteStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentDashboardBinding
import com.theworld.androidtemplatewithnavcomponents.ui.StringerViewModel
import com.theworld.androidtemplatewithnavcomponents.utils.Resource
import com.theworld.androidtemplatewithnavcomponents.utils.collectLatestFlow
import com.theworld.androidtemplatewithnavcomponents.utils.handleApiError
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard), StringerAdapter.StringerInterface {

    companion object {
        private const val TAG = "DashboardFragment"
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StringerViewModel by viewModels()

    private val stringerAdapter by lazy { StringerAdapter(this) }

    /*----------------------------------------- On Create View -------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    /*----------------------------------------- On ViewCreated -------------------------------*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setHasOptionsMenu(true)

        init()
        collectData()

    }

    private fun collectData() {

        collectLatestFlow(viewModel.fetchStringer()) { resource ->

            binding.loadingSpinner.isVisible = resource is Resource.Loading

            when (resource) {

                is Resource.Success -> {
                    val data = resource.value
                    stringerAdapter.submitList(data)

                    binding.notFound.isVisible = data.isEmpty()
                }
                is Resource.Failure -> {
                    handleApiError(resource)
                }

                else -> Unit
            }
        }

        /*collectLatestFlow(viewModel.isChanged) {

                viewModel.fetchStringer()

        }*/

    }


    /*----------------------------------------- Init -------------------------------*/


    private fun init() {

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = stringerAdapter
        }


        binding.fab.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToCreateStringerFragment()
            findNavController().navigate(action)
        }

    }


    override fun onClick(stringer: Stringer) {
        // We Can send the ID but API missing
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToEditStringerFragment(stringer)
        findNavController().navigate(action)
    }


    override fun onDelete(stringer: Stringer) {
        collectLatestFlow(viewModel.deleteStringer(DeleteStringerRequestData(stringer.stringerID))) { resource ->
            when (resource) {
                is Resource.Success -> {
//                    requireView().snackbar("${stringer.name} is deleted")
                    collectData()
//                    viewModel.isDataChanged()
                }
                is Resource.Failure -> {
                    handleApiError(resource)
                }
                else -> Unit
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)

    }


}