package com.obilet.android.assignment.feature.journeys.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.databinding.FragmentBusJourneysBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.journeys.component.BusJourneys
import com.obilet.android.assignment.feature.journeys.viewmodel.BusJourneysFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusJourneysFragment :
    BaseFragment<FragmentBusJourneysBinding>(FragmentBusJourneysBinding::inflate) {

    private val viewModel by viewModels<BusJourneysFragmentViewModel>()

    companion object {
        private const val JOURNEYS_BASE_URL = "https://www.obilet.com/seferler/"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setClickListeners()
        subscribeObservers()
    }

    private fun setupUI() {
        binding.busJourneys.apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val journeysResource by viewModel.busJourneys.observeAsState()
                when (journeysResource) {
                    is Resource.Error -> {
                        val message =
                            (journeysResource as Resource.Error).error?.asString(requireContext())
                                ?: getString(
                                    com.obilet.android.assignment.core.data.R.string.something_went_wrong
                                )
                        showErrorDialogWithOkayButton(message)

                    }

                    is Resource.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = colorResource(id = R.color.button_color),
                                modifier = Modifier.size(48.dp),
                            )
                        }
                    }

                    is Resource.Success -> {
                        BusJourneys(journeyList = journeysResource?.data ?: emptyList()) {
                            createRedirectionUrlThenNavigate(it)
                        }
                    }

                    null -> Unit
                }

            }

        }
    }

    private fun createRedirectionUrlThenNavigate(journey: BusJourney) {
        val url =
            JOURNEYS_BASE_URL + journey.originLocationId + "-" + journey.destinationLocationId + "/" + viewModel.args.departureDate + "/" + journey.id
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun subscribeObservers() {
        viewModel.toolbarTitle.observe(viewLifecycleOwner) { title ->
            binding.titleTv.text = title
        }
    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}