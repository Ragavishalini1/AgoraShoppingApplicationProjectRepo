Spring Boot RESTful Web Service Agora Shopping Application
===========================================================

This Application demonstrates the functionality of the Online Shopping Application called Agora.

Business cases:

1.Every User should be able to view the list of available items from catalog with the product's prescribed quantity
and size.
2.Every User will be able to place order with one or more items
3.The User can provide a customized quantity like 0.5 or 1.5
4.The User cannot give quantity which is less than 0.33
5.The User will receive a response with OrderId,total amount with discount,actual amount without discount,offers applied if any

 0 Prerequisite 
----------------------------
To use this project, you are going to need;

- Java JDK 8 (1.8)
- Maven compatibile with JDK 8
- Any Java IDE
- [Postman tool](https://www.getpostman.com/)


 1 Project Overview
-------------------
- [Entry Point](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/blob/master/src/main/java/com/agora/api/AgoraShoppingApplication.java)

- [controller package](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/tree/master/src/main/java/com/agora/api/controller)

   - AgoraShoppingController holds the API request mappings for (Getting Product Details and Placing Order)

- [service package](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/tree/master/src/main/java/com/agora/api/service)

- AgoraShoppingService class holds the business logic of fething the actual product from database and calculating the total amount based on offers and discounts.

- [repository package](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/tree/master/src/main/java/com/agora/api/data)

- [model package](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/tree/master/src/main/java/com/agora/api/model)

- There are three entity classes.
- Product - Holds the list of Products
- Offer - Holds the type of Offers
- Inventory - Holds the stock details of each Product


 
2-a List of Products
---------
```
Full URL: http://localhost:8080/agora/products
Method:   GET
Sends:    N/A
Receives: JSON
Sample Input: N/A
Sample Output;
[
    {
        "productId": 1,
        "productName": "MEIJI MILK",
        "productSize": 500.0,
        "units": "ml",
        "productPrice": 3.50,
        "offer": {
            "offerId": "2",
            "offerDescription": "DISC5"
        }
    },
    {
        "productId": 2,
        "productName": "HEINZ KETCHUP",
        "productSize": 250.0,
        "units": "ml",
        "productPrice": 2.50,
        "offer": {
            "offerId": "1",
            "offerDescription": "B1G1"
        }
    },
    {
        "productId": 3,
        "productName": "RICE",
        "productSize": 500.0,
        "units": "gm",
        "productPrice": 3.50,
        "offer": {
            "offerId": "2",
            "offerDescription": "DISC5"
        }
    },
    {
        "productId": 4,
        "productName": "DHAL",
        "productSize": 500.0,
        "units": "gm",
        "productPrice": 2.50,
        "offer": {
            "offerId": "4",
            "offerDescription": "FREEBOX"
        }
    },
    {
        "productId": 5,
        "productName": "AATA",
        "productSize": 1000.0,
        "units": "gm",
        "productPrice": 4.50,
        "offer": {
            "offerId": "3",
            "offerDescription": "DISC10"
        }
    }
]
```

 2-b Place Order
----------------
```
Full URL: http://localhost:8080/agora/order
Method:   POST
Sends:    JSON
Receives: JSON

Sample Input;
{
"selectedItemList" : 
[
	{ "itemId" : 100,
	  "quantity" : 0.5
	}

]
}

Sample Output;
{
    "orderId": "c27ceddf-111e-47d6-93fb-6ddff2a6d4d4",
    "noOfItems": 1,
    "offerApplied": "DISC5",
    "actualAmountToPay": 3.500,
    "discountApplied": 5,
    "amountAfterDiscount": 3.325,
    "errorResponse": []
}
```
 3.Handled below test cases with code coverage of 90 percent
--------------------------------------------------------------------------------
[AgoraShoppingApplicationIT](https://github.com/Ragavishalini1/AgoraShoppingApplicationProjectRepo/tree/master/src/main/java/com/agora/api/service)
- Retrieve List of Products
-  Place Order For One Product
-  Place Order for Product with offer 5 percent discount
-  Place Order for Product with offer 10 percent discount
-  Place Order for Product with offer Buy One get One
-  Place Order for Product with offer Free Box
-  Place Order With Product List having Invalid Item Code
-  Place Order With Duplicate Items



