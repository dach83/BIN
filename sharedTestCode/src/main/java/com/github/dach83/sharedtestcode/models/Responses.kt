package com.github.dach83.sharedtestcode.models

const val errorResponse = "I'm not a JSON"

val visaCardResponse = """
        {
          "number": {
            "length": 16,
            "luhn": true
          },
          "scheme": "visa",
          "type": "debit",
          "brand": "Visa/Dankort",
          "prepaid": false,
          "country": {
            "numeric": "208",
            "alpha2": "DK",
            "name": "Denmark",
            "emoji": "🇩🇰",
            "currency": "DKK",
            "latitude": 56,
            "longitude": 10
          },
          "bank": {
            "name": "Jyske Bank",
            "url": "www.jyskebank.dk",
            "phone": "+4589893300",
            "city": "Hjørring"
          }
        }
""".trimIndent()
