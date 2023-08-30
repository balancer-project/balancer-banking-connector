package io.juancrrn.balancerbankingconnector.domain.exceptions

import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId

class UserAndInstitutionAlreadyLinkedException(
    val userId: UserId,
    val institutionId: InstitutionId,
) : DomainException(
    "user_and_institution_already_linked",
    "User \"${userId.id}\" and institution \"${institutionId.id}\" are already linked.",
)
