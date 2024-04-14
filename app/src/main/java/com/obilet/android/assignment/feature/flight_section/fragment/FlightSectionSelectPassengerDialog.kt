package com.obilet.android.assignment.feature.flight_section.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import com.obilet.android.assignment.databinding.DialogFlightSectionSelectPassengerBinding
import com.obilet.android.assignment.feature.flight_section.component.PassengerFilterList
import com.obilet.android.assignment.feature.flight_section.viewmodel.FlightSectionSelectPassengerDialogViewModel
import com.obilet.android.assignment.utils.setNavigationResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightSectionSelectPassengerDialog : BottomSheetDialogFragment() {

    private var _binding: DialogFlightSectionSelectPassengerBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<FlightSectionSelectPassengerDialogViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFlightSectionSelectPassengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setClickListeners()
    }

    private fun subscribeObservers() {
        viewModel.passengerFilterList.observe(viewLifecycleOwner) { filterList ->
            binding.passengerFilterList.setContent {
                PassengerFilterList(
                    filterList = filterList,
                    setIncreaseDecreaseStatusOfButtons = { filter ->
                        val mutableFilterList = createNewMutableListFromTheFilterList(filterList)
                        val adultList = createAdultListFromFilterList(mutableFilterList)
                        val sumOfAdults = getSumOfAdultsInTheAdultList(adultList)
                        if (filter == PassengerFilter.BABY) {
                            filter.canDecrease =
                                setBabyItemCanDecreaseIfCountOfTheBabyItemGreaterThanZero(filter)
                            filter.canIncrease =
                                setBabyItemCanIncreaseIfCountOfTheBabyItemLessThanSumOfAdults(
                                    filter,
                                    sumOfAdults
                                )
                            val indexOfBaby =
                                findTheIndexOfBabyItemFromTheList(mutableFilterList, filter)
                            saveTheModifiedItemToTheFilterList(
                                mutableFilterList,
                                indexOfBaby,
                                filter
                            )
                            setTheAdultItemsCanIncreaseDecrease(
                                adultList,
                                mutableFilterList,
                                sumOfAdults,
                                filter
                            )
                            viewModel.setPassengerFilterList(mutableFilterList)
                        } else {
                            val sumOfItemsExceptBaby =
                                getSumOfItemsFromFilterListExceptBabyItem(mutableFilterList)
                            mutableFilterList.forEachIndexed { index, item ->
                                if (item != PassengerFilter.BABY) {
                                    item.canDecrease = if (item.adult) {
                                        adultList.sumOf { it.count } > 1
                                    } else {
                                        item.count > 0
                                    }
                                    item.canIncrease = sumOfItemsExceptBaby < 4
                                } else {
                                    item.canIncrease =
                                        setBabyItemCanIncreaseIfCountOfTheBabyItemLessThanSumOfAdults(
                                            item,
                                            sumOfAdults,
                                        )
                                    item.canDecrease =
                                        setBabyItemCanDecreaseIfCountOfTheBabyItemGreaterThanZero(
                                            item
                                        )
                                }
                                saveTheModifiedItemToTheFilterList(
                                    mutableFilterList,
                                    index,
                                    item
                                )
                            }
                            viewModel.setPassengerFilterList(mutableFilterList)
                        }
                    }
                )
            }
        }
    }

    private fun createNewMutableListFromTheFilterList(filterList: List<PassengerFilter>): MutableList<PassengerFilter> {
        return filterList.toMutableList()
    }

    private fun createAdultListFromFilterList(filterList: List<PassengerFilter>): List<PassengerFilter> {
        return filterList.filter { it.adult }
    }

    private fun getSumOfAdultsInTheAdultList(adultList: List<PassengerFilter>): Int {
        return adultList.sumOf { it.count }
    }

    private fun setBabyItemCanDecreaseIfCountOfTheBabyItemGreaterThanZero(babyItem: PassengerFilter): Boolean {
        return babyItem.count > 0
    }

    private fun findTheIndexOfBabyItemFromTheList(
        filterList: List<PassengerFilter>,
        babyItem: PassengerFilter
    ): Int {
        return filterList.indexOf(babyItem)
    }

    private fun setBabyItemCanIncreaseIfCountOfTheBabyItemLessThanSumOfAdults(
        babyItem: PassengerFilter,
        sumOfAdults: Int
    ): Boolean {
        return babyItem.count < sumOfAdults
    }

    private fun saveTheModifiedItemToTheFilterList(
        filterList: MutableList<PassengerFilter>,
        indexOfItem: Int,
        item: PassengerFilter,
    ) {
        filterList[indexOfItem] = item
    }

    private fun setTheAdultItemsCanIncreaseDecrease(
        adultList: List<PassengerFilter>,
        filterList: MutableList<PassengerFilter>,
        sumOfAdults: Int,
        babyItem: PassengerFilter
    ) {
        adultList.forEach { adult ->
            adult.canDecrease =
                adult.count > 0 && babyItem.count < sumOfAdults
            val indexOfAdult = filterList.indexOf(adult)
            filterList[indexOfAdult] = adult
        }
    }

    private fun getSumOfItemsFromFilterListExceptBabyItem(filterList: List<PassengerFilter>): Int {
        return filterList.filter { it != PassengerFilter.BABY }
            .sumOf { it.count }
    }

    private fun setClickListeners() {
        binding.closeIb.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}