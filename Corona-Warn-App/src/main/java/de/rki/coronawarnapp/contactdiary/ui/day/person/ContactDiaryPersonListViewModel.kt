package de.rki.coronawarnapp.contactdiary.ui.day.person

import androidx.lifecycle.asLiveData
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import de.rki.coronawarnapp.contactdiary.storage.ContactDiaryRepository
import de.rki.coronawarnapp.util.viewmodel.CWAViewModel
import de.rki.coronawarnapp.util.viewmodel.CWAViewModelFactory
import kotlinx.coroutines.flow.map
import org.joda.time.Instant

class ContactDiaryPersonListViewModel @AssistedInject constructor(
    @Assisted selectedDay: Long,
    contactDiaryRepository: ContactDiaryRepository
) : CWAViewModel() {

    private val dayElement = contactDiaryRepository.filterForDay(Instant.ofEpochMilli(selectedDay))

    val persons = dayElement.map { it.people }.asLiveData()

    @AssistedInject.Factory
    interface Factory : CWAViewModelFactory<ContactDiaryPersonListViewModel> {
        fun create(selectedDay: Long): ContactDiaryPersonListViewModel
    }
}