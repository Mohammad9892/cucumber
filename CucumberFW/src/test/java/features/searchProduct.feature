Feature: Search and Place the Order for Products

Scenario: Search Experience for product search in both home and Offers Page

Given User is on GreenCart Landing page
When user searched with Shortname "Tom" and extracted actual name of product
Then user search for the "Tom" shortname in offer page
And validate product name in offers page with Landing Page