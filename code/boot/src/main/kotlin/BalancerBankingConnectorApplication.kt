package com.docline.prescriptionasproduct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.juancrrn"])
class BalancerBankingConnectorApplication

fun main(args: Array<String>) {
    runApplication<BalancerBankingConnectorApplication>(*args)
}
