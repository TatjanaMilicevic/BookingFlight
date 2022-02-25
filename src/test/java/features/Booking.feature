Feature: Booking

  Scenario: Book a Flight

      # Given I load test data from "Booking" "BookingSheet" "<row>"
    Given I navigate to Booking
    Then I navigate to flights page
    And I select flight class "PREMIUM_ECONOMY"
    And I select number of adults "3"
    And I add departure and return date "2022-02-27" "2022-02-28"
    And I add destination
    And I check direct flights only
    And I click search
    Then I choose stops and flight time "1 stop max" "6:00 AM - 11:59 AM"



#    Examples:
#      | row |
#      | 1   |
#      | 2   |