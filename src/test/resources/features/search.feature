Feature: Search



#  Scenario: Create a new pet card
#    Given Tomas fill the required data to create a card
#    When Tomas keep the pet card
#    Then The kept data is returned with new id
#
#  Scenario: Find a new pet card
#    Given Tomas create cards
#      | Name | type |
#      | Misu | Cat  |
#      | Any  | Dog  |
#    And Tomas tries to find the cards created
#    Then verify cards exists
#
#  Scenario: Find an pet card which not exists
#    And Tomas find a id
#      | id  |
#      | 1ax |
#    Then verify cards does not exist
#
#  Scenario: Update an pet card
#    Given Tomas create cards
#      | Name | type |
#      | Misu | Cat  |
#      | Any  | Dog  |
#    And Tomas update cards
#      | Name  | type  |
#      | Juan  | Horse |
#      | Tomas | Dog   |
#    Then verify cards has been updated
#
#  Scenario: Update an pet card which not exist
#    And Tomas update cards wich not exist
#      | Name | type  |
#      | Juan | Horse |
#    Then verify cards updated does not exist
#
#  Scenario: Add owner
#    Given Tomas create cards
#        | Name | type |
#        | Misu | Cat  |
#    And User is created
#    When Owner is added
#    Then card has the owner
