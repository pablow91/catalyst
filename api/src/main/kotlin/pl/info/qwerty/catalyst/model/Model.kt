package pl.info.qwerty.catalyst.model

data class LastTransaction(
        val lastTransaction: String,
        val lastTransactionVolume: Int,
        val lastPrice: Double
)

data class Triple1(
        val volume: Int,
        val ordersCount: Int,
        val price: Double
)

data class Bond(
        val marketName: String,
        val issuer: String,
        val name: String,
        val segment: String,
        val transactionUnit: Int,
        val change: Double?,
        val referencePrice: Double?,
        val openingPrice: Double?,
        val minimalPrice: Double?,
        val maximalPrice: Double?,
        val lastTransaction: LastTransaction?,
        val buyTriple: Triple1?,
        val sellTriple: Triple1?,
        val additionalInfo: AdditionalInfo
)

data class AdditionalInfo(
        val beginDate: String,
        val endDate: String,
        val interestType: String,
        val issueValue: Double,
        val faceValue: Double,
        val currentInterestRate: Double,
        val cumulativeInterest: Double
)

data class Market(
        val id: Int,
        val name: String
)