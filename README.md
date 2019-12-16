# ShopMax

Welcome to **ShopMax**! This is a Shopping website where you can buy everything, although they are all fake :)  

The project was built by Yifan Zhang with **Spring**, **Spring MVC** and **Hibernate**.  

The front-end side was developed by **Bootstrap** and **Jquery**, and the database is **Mysql**.  

# Install  

This project was created by Maven and developed with Java9 in Intellij IDEA.  

Please execute all queries in **db/shopping.sql** to create database, table and insert data.

If the 'shopping' database already exists in your localhost and you don't want to delete it:

You could change the create database sql in shopping.sql, and config the **src/main/resources/properties/database.properties**    

After creating the database, you could config the tomcat and then run in Intellij IDEA.

The dependencies in maven may require some time to clone from central repository.

# Usage

Below is things you can do in this shopping website.

1. Login, Register (including restriction on username/password)  

2. Find all products and their image in main page.  

3. Click the image of any product to view the details of the product.

4. Add products to ShoppingCart (doesn't require login) or WishList (require login)

5. Add items to your shopping cart without login, and it will automatically merge to the shopping cart when you log in.

6. Modify the amount of products you want to buy at the shopping cart page.

7. Move items from shopping cart to wishList and from wishList to shopping cart.

# Screenshot
Below is some screenshots of the project.

**Main Page of ShopMax**
![ScreenShot](https://raw.githubusercontent.com/FanFan0919/ShopMax/master/screenshots/index.png)


**Register and Login**
![ScreenShot](https://raw.githubusercontent.com/FanFan0919/ShopMax/master/screenshots/login.png)
![ScreenShot](https://raw.githubusercontent.com/FanFan0919/ShopMax/master/screenshots/register.png)


**Product Details**
![ScreenShot](https://raw.githubusercontent.com/FanFan0919/ShopMax/master/screenshots/product_detail.png)




