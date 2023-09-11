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


![swagger](https://github.com/iunter/challenge-trafilea/assets/18707501/2edaef00-485a-4c3f-a9cc-3c29a748b526)

### Opening the menus will show the following

![swagger 2](https://github.com/iunter/challenge-trafilea/assets/18707501/238f8899-1c3f-46e4-97ac-2390a01a5313)

### Clicking on the methods the information on how to execute the endpoint will show alongside with description

![swagger 3](https://github.com/iunter/challenge-trafilea/assets/18707501/bb156d1e-4d88-4bb6-a028-71dc9cfdd944)

### To try press on the button that says *Try it out*, input the necessary data and then press execute.

![swagger 4](https://github.com/iunter/challenge-trafilea/assets/18707501/21bf407c-7175-4d57-8118-bbb2412a03fd)

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

![h2 console](https://github.com/iunter/challenge-trafilea/assets/18707501/2837c3e8-7924-4953-854d-a09f60150a0a)

### Once connected the screen should look something like the following

![h2 console 2](https://github.com/iunter/challenge-trafilea/assets/18707501/6592fedd-2e89-492e-b2ad-3e706888791a)
