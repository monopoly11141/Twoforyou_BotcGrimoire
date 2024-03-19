package com.example.twoforyou_botcgrimoire.domain.constant

import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character

class TROUBLE_BREWING_CHARACTERS {
    companion object {
        private val characterChef = Character(
            name = "요리사Chef",
            ability = "[첫 밤] 서로 인접해 앉아있는 악팀이 전체 몇 쌍인지 알고 게임을 시작합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 0,
            reminderTokens = emptyList(),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d5/Icon_chef.png",
            firstNightOrder = 5,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterInvestigator = Character(
            name = "조사자Investigator",
            ability = "[첫 밤] 이야기꾼이 지목한 플레이어 2명 중 1명이 특정 하수인이라는 것을 알고 게임을 시작합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 2,
            reminderTokens = listOf("하수인MINION", "거짓WRONG"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/a/ad/Icon_investigator.png",
            firstNightOrder = 4,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterWasherwoman = Character(
            name = "세탁부Washerwoman",
            ability = "[첫 밤] 이야기꾼이 지목한 플레이어 2명 중 1명이 특정 마을주민이라는 것을 알고 게임을 시작합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 2,
            reminderTokens = listOf("마을주민TOWNSFOLK", "거짓WRONG"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/8/85/Icon_washerwoman.png",
            firstNightOrder = 2,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterLibrarian = Character(
            name = "사서Librarian",
            ability = "[첫 밤] 이야기꾼이 지목한 플레이어 2명 중 1명이 특정 외부인이라는 것을 알고 게임을 시작합니다. 외부인이 없다면 0을 알게 됩니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 2,
            reminderTokens = listOf("외부인OUTSIDER", "거짓WRONG"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/e/e0/Icon_librarian.png",
            firstNightOrder = 3,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterEmpath = Character(
            name = "공감인Empath",
            ability = "[매일 밤] 당신 양쪽으로 가장 인접한 살아있는 플레이어 중 몇 명이 악팀인지 알게 됩니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 0,
            reminderTokens = emptyList(),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/1/13/Icon_empath.png",
            firstNightOrder = 6,
            otherNightOrder = 6,
            isFormatChangingRole = false
        )

        private val characterFortuneTeller = Character(
            name = "점쟁이FortuneTeller",
            ability = "[매일 밤] 플레이어 2명을 지목합니다: 2명 중 악마가 있다면, “예”라는 대답을 얻습니다. 선팀 플레이어 중 1명은 당신에게 악마로 보입니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 1,
            reminderTokens = listOf("악마로_보임Red_Herring"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/c/c3/Icon_fortune_teller.png",
            firstNightOrder = 7,
            otherNightOrder = 7,
            isFormatChangingRole = false
        )

        private val characterUndertaker = Character(
            name = "장의사Undertaker",
            ability = "[매일 밤*] 당신은 오늘 처형되어 죽은 플레이어의 캐릭터를 알게 됩니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 1,
            reminderTokens = listOf("오늘_처형_죽음DIED_TODAY"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/0/05/Icon_undertaker.png",
            firstNightOrder = 7,
            otherNightOrder = 7,
            isFormatChangingRole = false
        )

        private val characterMonk = Character(
            name = "수도승Monk",
            ability = "[매일 밤*] 본인을 제외한 플레이어 1명을 선택합니다: 해당 플레이어는 오늘 밤 악마의 부정적인 능력(살해, 중독 등등)으로부터 안전합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 1,
            reminderTokens = listOf("안전SAFE"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/7/7c/Icon_monk.png",
            firstNightOrder = null,
            otherNightOrder = 7,
            isFormatChangingRole = false
        )

        private val characterSlayer = Character(
            name = "처단자Slayer",
            ability = "[게임당 1번] 낮에 공개적으로 처단자라고 선언하고 플레이어 1명을 선택합니다: 해당 플레이어가 악마라면, 그 플레이어는 사망합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 1,
            reminderTokens = listOf("능력_없음NO_ABILITY"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d3/Icon_slayer.png",
            firstNightOrder = null,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterSoldier = Character(
            name = "군인Soldier",
            ability = "당신은 악마의 부정적인 능력(살해, 중독 등등)으로부터 안전합니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 0,
            reminderTokens = emptyList(),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/c/c3/Icon_soldier.png",
            firstNightOrder = null,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterRavenkeeper = Character(
            name = "까마귀사육사Ravenkeeper",
            ability = "당신이 밤에 사망하면 그 밤에 일어나 플레이어 1명을 선택합니다: 해당 플레이어의 캐릭터를 알게 됩니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 0,
            reminderTokens = emptyList(),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/e/ef/Icon_ravenkeeper.png",
            firstNightOrder = null,
            otherNightOrder = 5,
            isFormatChangingRole = false
        )

        private val characterVirgin = Character(
            name = "아가씨Virgin",
            ability = "당신이 처음으로 처형 투표로 지목될 때 : 지목한 플레이어가 마을주민이라면 해당 마을주민이 처형되며 낮이 즉시 종료됩니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 1,
            reminderTokens = listOf("능력_없음NO_ABILITY"),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/d/d3/Icon_virgin.png",
            firstNightOrder = null,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        private val characterMayor = Character(
            name = "시장Mayor",
            ability = "살아있는 플레이어가 3명일 때 처형이 일어나지 않는다면 당신의 팀이 승리합니다. 당신이 저녁에 죽으려고 할 때, 이야기꾼은 대신 다른 플레이어를 죽일 수 있습니다.",
            type = Character_Type.마을주민_TOWNSFOLK,
            fromWhichEdition = Edition.소란발생_TROUBLE_BREWING,
            numberOfReminderTokens = 0,
            reminderTokens = emptyList(),
            imageUrl = "https://wiki.bloodontheclocktower.com/images/a/a1/Icon_mayor.png",
            firstNightOrder = null,
            otherNightOrder = null,
            isFormatChangingRole = false
        )

        val CHARACTERS = hashMapOf(
            "요리사Chef" to characterChef,
            "조사자Investigator" to characterInvestigator,
            "세탁부Washerwoman" to characterWasherwoman,
            "사서Librarian" to characterLibrarian,
            "공감인Empath" to characterEmpath,

            "점쟁이FortuneTeller" to characterFortuneTeller,
            "장의사Undertaker" to characterUndertaker,
            "수도승Monk" to characterMonk,
            "처단자Slayer" to characterSlayer,
            "군인Soldier" to characterSoldier,

            "까마귀사육사Ravenkeeper" to characterRavenkeeper,
            "아가씨Virgin" to characterVirgin,
            "시장Mayor" to characterMayor,
        )
    }
}