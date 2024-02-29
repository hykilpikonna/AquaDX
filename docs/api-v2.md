

### CardController : /api/v2/card

Located at: [icu.samnyan.aqua.net.CardController](icu/samnyan/aqua/net/CardController.kt)

**/card/link** : Bind a card to the user. This action will migrate selected data from the card to the user's ghost card.

* token: String
* cardId: String
* migrate: String
* **Returns**: Success message

**/card/summary** : Get a summary of the card, including the user's name, rating, and last login date.

* cardId: String
* **Returns**: Summary of the card

**/card/unlink** : Unbind a card from the user. No data will be migrated during this action.

* token: String
* cardId: String
* **Returns**: Success message


### UserRegistrar : /api/v2/user

Located at: [icu.samnyan.aqua.net.UserRegistrar](icu/samnyan/aqua/net/UserRegistrar.kt)

**/user/confirm-email** : Confirm email address with a token sent through email to the user.

* token: String
* **Returns**: Success message

**/user/me** : Get the information of the current logged-in user.

* token: String
* **Returns**: User information

**/user/login** : Login with email/username and password. This will also check if the email is verified and send another confirmation

* email: String
* password: String
* turnstile: String
* **Returns**: JWT token

**/user/register** : Register a new user. This will also create a ghost card for the user and send a confirmation email.

* username: String
* email: String
* password: String
* turnstile: String
* **Returns**: Success message

**/user/setting** : Validate and set a user setting field.

* token: String
* key: String
* value: String
* **Returns**: Success message
