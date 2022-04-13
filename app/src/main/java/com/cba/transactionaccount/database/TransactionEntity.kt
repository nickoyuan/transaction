package com.cba.transactionaccount.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cba.transactionaccount.util.Constants.TRANSACTION_HISTORY_TABLE
import org.joda.time.LocalDate

@Entity(tableName = TRANSACTION_HISTORY_TABLE)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false) val id : String,
    @ColumnInfo("amount") val amount : String,
    @ColumnInfo("isPending") val isPending: Boolean,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("effectiveDate") val effectiveDate: LocalDate,
)