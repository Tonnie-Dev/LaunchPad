package com.uxstate.launchpad.data.local.database.transaction

import com.uxstate.launchpad.data.local.database.LaunchDatabase
import javax.inject.Inject

class LaunchDatabaseTransactionProvider @Inject constructor(
    database: LaunchDatabase,
) : RoomDatabaseTransactionProvider(database)