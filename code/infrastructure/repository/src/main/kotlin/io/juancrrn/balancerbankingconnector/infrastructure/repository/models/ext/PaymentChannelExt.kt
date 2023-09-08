package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import com.plaid.client.model.Transaction.PaymentChannelEnum
import com.plaid.client.model.Transaction.PaymentChannelEnum.IN_STORE
import com.plaid.client.model.Transaction.PaymentChannelEnum.ONLINE
import com.plaid.client.model.Transaction.PaymentChannelEnum.OTHER
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PaymentChannel as PaymentChannelVO

fun PaymentChannelEnum.toVO(): PaymentChannelVO {
    return when (this) {
        ONLINE -> PaymentChannelVO.ONLINE
        IN_STORE -> PaymentChannelVO.IN_STORE
        OTHER -> PaymentChannelVO.OTHER
    }
}
