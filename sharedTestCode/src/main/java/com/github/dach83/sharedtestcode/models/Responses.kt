package com.github.dach83.sharedtestcode.models

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

val masterCardResponse = """
        {
          "number": {},
          "scheme": "mastercard",
          "type": "credit",
          "brand": "Standard",
          "country": {
            "numeric": "643",
            "alpha2": "RU",
            "name": "Russian Federation",
            "emoji": "🇷🇺",
            "currency": "RUB",
            "latitude": 60,
            "longitude": 100
          },
          "bank": {
            "name": "VTB24",
            "url": "www.vtb.com",
            "phone": "(800) 100-24-24"
          }
        }
""".trimIndent()
