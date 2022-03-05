Feature: Booking

  Scenario: Book a Flight

      # Given I load test data from "Booking" "BookingSheet" "<row>"
    Given I navigate to Booking
    Then I navigate to flights page
    And I select flight class "PREMIUM_ECONOMY"
    And I select number of adults "3"
    And I add departure and return date "2022-03-11" "2022-03-10"
    And I add destination "MIAMI"
    And I check direct flights only
    And I click search
    Then I choose stops and flight time "1 stop max" "6:00 AM - 11:59 AM"
    Then I choose presentation "Cheapest"
    Then I check price
    And select flight

    And I select ticket type "Flexible ticket"

    And I enter contact data "RS"
    Then I enter passenger data "2" "M" "15" "02" "1986"
    Then I click next button

    Then I select mealOption "3" "VEGAN"

    Then I select seats

    Then I open payment page and verify price















#    Examples:
#      | row |
#      | 1   |
#      | 2   |