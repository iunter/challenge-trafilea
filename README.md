# Trafilea Challenge Ivan Unterberger Bauni

## How to run
Placed on the root directory of the project run the following commands


``` powershell
docker compose build
```
Once it finished building run
``` powershell
docker compose up
```

## Accesing swagger-ui
### go to http://localhost:8080/swagger-ui/#/ and a screen like this will appear

![swagger](https://github.com/iunter/challenge-trafilea/assets/18707501/17c4de1e-33a0-47a4-9ff8-aa91afabd5d7)


### Opening the menus will show the following

![swagger 2](https://github.com/iunter/challenge-trafilea/assets/18707501/af74dd9a-a7b4-41e2-b151-c89c81780dbd)

### Clicking on the methods the information on how to execute the endpoint will show alongside with description

![swagger 3](https://github.com/iunter/challenge-trafilea/assets/18707501/e49be6c0-5c52-40ce-8ffe-b9ac7af89561)

### To try press on the button that says *Try it out*, input the necessary data and then press execute.

![swagger 4](https://github.com/iunter/challenge-trafilea/assets/18707501/95ec4c04-2d85-45a4-828b-abe3b904dea6)

### It is recomended to test the application utilizing swagger since it is well documented and it has access to request examples. 

## Execution secuence

- Create a new cart for an specific user.
  - If there is already an active cart a message will show.
- Add or modify Items to the cart using the userId.
  - The same endpoint is used for both actions.
- Create the order.
  - Once the order is created the cart will be marked as innative and a new cart can now be created for the user.

## Accesing H2 Database console
### go to http://localhost:8080/console and complete the login like follows
- Password = "password"
- JDBC URL = "jdbc:h2:mem:testdb"

![h2 console](https://github.com/iunter/challenge-trafilea/assets/18707501/44311811-a657-45dc-b3c6-df5ceb59a898)

### Once connected the screen should look something like the following

![h2 console 2](https://github.com/iunter/challenge-trafilea/assets/18707501/b1cd777e-31f7-4a2e-bd37-c613b1bc450b)
