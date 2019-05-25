# backendChallenge

## Requirements
This application was developed and tested with the following specifications:

- Java JDK 8
- MySQL 5.6.26
- Maven 4
- Apache Commons

## Installation
To install this application first you need to make your environment ready as specified in requirements section.
Then clone this repository and run the following on command line:
git clone [repo_url]`
- `mvn install`
- `mysql -u [username] -p [database_name] < tshirtshop.sql`
- `Change the configuration file accordingly ("src/main/resources/application.properties")` 

## We can now hit the API and get back the right results with any combination of criteria. For example –

## To test search API: 
http://localhost:8080/products?search=product_id:<50

## And the response:

[
  {
    "product_id": 50,
    "name": "Somali Ostriches",
    "description": "Another in an old series of beautiful stamps from Ethiopia. These big birds pack quite a wallop, and so will you when you wear this uniquely retro T-shirt!",
    "price": "12.95",
    "discounted_price": "0.00",
     "thumbnail": "somali-ostriches-thumbnail.gif"
  }
]
