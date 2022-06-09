package com.theworld.androidtemplatewithnavcomponents.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theworld.androidtemplatewithnavcomponents.R
import com.theworld.androidtemplatewithnavcomponents.adapters.StringerAdapter
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.DeleteStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentDashboardBinding
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentHomeBinding
import com.theworld.androidtemplatewithnavcomponents.ui.StringerViewModel
import com.theworld.androidtemplatewithnavcomponents.utils.Resource
import com.theworld.androidtemplatewithnavcomponents.utils.collectLatestFlow
import com.theworld.androidtemplatewithnavcomponents.utils.handleApiError
import com.theworld.androidtemplatewithnavcomponents.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    /*----------------------------------------- On Create View -------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    /*----------------------------------------- On ViewCreated -------------------------------*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        init()

    }


    /*----------------------------------------- Init -------------------------------*/


    private fun init() {


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.createStringerFragment) {
            val action = HomeFragmentDirections.actionHomeFragmentToDashboardFragment()
            findNavController().navigate(action)
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    }


    /*----------------------------------------- On DestroyView -------------------------------*/

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}