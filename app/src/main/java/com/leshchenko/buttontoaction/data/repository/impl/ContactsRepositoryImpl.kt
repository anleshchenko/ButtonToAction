package com.leshchenko.buttontoaction.data.repository.impl

import android.content.Context
import com.leshchenko.buttontoaction.data.repository.ContactsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
) : ContactsRepository {

    override fun callToContact() {
        // TODO Call to contact
    }
}