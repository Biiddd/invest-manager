## 1. Requirement Analyze
By reading the task sheet,
## 2. Design
为了保证功能间的解耦，应该要拆分函数，不应把函数写的过于复杂。对于循环的处理应该尽早捕获异常。  
To ensure decoupling between functions, it's essential to split functions and avoid making them overly complex. Regarding handling loops, it's advisable to catch exceptions as early as possible.
### 2.1 Total Design
Total design has already mentioned in task sheet, which should contain Market, Company, Investor, BasicStrategy.

Market has a static object
Company has these objects
Investor has these objects
BasicStrategy has these objects

### 2.2 OOP
功能设计遵循面向对象的原则，在这个项目中，不适合使用继承，市场应该作为一个单独的类和对象来管理公司。
所有的类的对象均为private类型保证属性不能被随意访问和修改，若想修改只能通过类中对应的方法进行修改。所有类都有一个自己的构造函数用来创建对象。
其中BasicStrategy类中的购买股票和出售股票方法使用了protected类型，这样即保证了一定的安全性，同时也使得Controller类调用这两个方法更方便
The functional design adheres to object-oriented principles. In this project, inheritance is deemed inappropriate. Instead, the market should be managed as a separate class and object.
All objects of the classes are set to private, ensuring that attributes cannot be accessed or modified arbitrarily. Any modifications must be performed through the corresponding methods within each class. Each class has its own constructor for object instantiation.
In the BasicStrategy class, the methods for purchasing and selling stocks are designated as protected. This ensures a certain level of security while also facilitating more convenient invocation of these methods by the Controller class.

## 3. Test
测试均放在Controller类中执行，代码中已经覆盖所有的方法，在报告中我将按类来进行描述。
测试的逻辑为：创建公司并将公司加入到市场，然后创建一个投资者，在wallet参数中可以修改投资者的启动资金。投资者手动将公司加入到自己的感兴趣列表中，然后观察所有自己感兴趣的公司股价.如果股价出现了上升，那么会进行购入操作，购入的数量sharesToBuy = maxTransaction / currentPrice，并且向下取整。如果股价出现了下跌，那么将售出股票，售出的数量 sharesToSell = maxTransaction / currentPrice, 需要注意的是，在这里会出现我当前持有的股票小于前面计算出来的sharesToSell, 这时候需要重新赋值sharesToShell为我现在持有的数量。这样的模拟会循环update次，update参数可以在Controller类中的58行进行修改。
我的流程图如下：  

All tests are executed in the Controller class. The code covers all methods, and in the report, I will describe them by class.
The testing logic is as follows: create companies and add them to the market, then create an investor. The starting capital of the investor can be modified in the wallet parameter. The investor manually adds companies to their list of interests and then observes the stock prices of all the companies they are interested in. If the stock price rises, a buy operation is performed, where the number of shares to buy is calculated as sharesToBuy = maxTransaction / currentPrice, rounded down. If the stock price falls, shares will be sold, and the number of shares to sell is calculated as sharesToSell = maxTransaction / currentPrice. Note that if the current number of shares held is less than the calculated sharesToSell, sharesToSell needs to be reassigned to the current number of shares held. This simulation will loop for update times, and the update parameter can be modified at line 58 in the Controller class.
Here is my flowchart:

### 3.1 Market class
市场类中涉及到三个方法add, remove, getCompanies. 先创建四个公司，然后将他们添加到市场中，其中我们重复添加一次Company C, 看一下程序的输出为：
然后我们删除Company C, 删除时需要判断是否存在，如果存在则直接删除，不存在则抛出异常。删除一个不在市场中的公司测试一下异常的捕获，程序的输出如下:

In the `Market` class, there are three methods involved: `add`, `remove`, and `getCompanies`. First, create four companies, then add them to the market, with `Company C` being added twice. Observe the program output:

Next, delete `Company C`. When deleting, check if it exists; if it does, delete it directly, otherwise, throw an exception. Test the exception handling by attempting to delete a company that is not in the market. The program output is as follows:

### 3.2 Company class
公司类中有许多获取和更新属性的方法，在这里不进行赘述。其中updateTrend方法比较重要. 我们设置一个currentTrend变量来存储当前的股价趋势。计算的|s2-s1|/s1的时候需要注意：虽然在股价计算公式中，新股价始终大于零，但是它可以很接近0, 由于股价是取小数点后两位，所以是有出现0的可能，并且在我的测试中确实出现过，所以需要捕获异常进行处理，处理异常的代码如下：

In the Company class, there are numerous methods for accessing and updating attributes, which will not be elaborated upon here. Among them, the updateTrend method holds particular significance. We employ a currentTrend variable to store the current stock price trend. When calculating |s2 - s1| / s1, it's essential to note that while the new stock price is typically greater than zero according to the stock price calculation formula, it can approach zero. Since stock prices are rounded to two decimal places, the possibility of reaching zero exists. Indeed, such occurrences have been observed in my testing. Therefore, it's imperative to handle exceptions gracefully. Below is the exception handling code:

### 3.3 Investor class
Investor类比较简单，Controller类中我们添加CompanyX和CompanyA测试addInterest方法，添加完成BasicStrategy类中的方法比较简单，只有两个方法buyStock和sellStock，这两个方法在Controller类中进行了测试，测试逻辑为：创建一个公司，然后创建一个投资者，将公司加入到投资者的感兴趣公司列表中，然后调用buyStock方法，将公司的股票购入，然后调用sellStock方法，将公司的股票售出，代码输出如下：后调用investor.getCompaniesOfInterest方法输出一下当前感兴趣公司的列表，然后使用方法removeInterest将CompanyA移出感兴趣公司列表，再次输出当前感兴趣公司列表，检查是否移除成功，代码输出如下：   
Investor class is relatively simple. In the Controller class, we add CompanyX and CompanyA to test the addInterest method. After adding them, we call investor.getCompaniesOfInterest method to output the current list of interested companies. Then, we use the removeInterest method to remove CompanyA from the list of interested companies. We output the current list of interested companies again to check if the removal was successful. The code output is as follows:

### 3.4 BasicStrategy class
BasicStrategy类中的`invest`方法中包含了购买和出售股票的逻辑，包含前文提到的在什么时候进行购买或者售出股票。  
`sharesToBuy`方法包含了购买股票的逻辑，其中包含了买多少股票的计算， `sharesToSell`方法包含了售出股票的逻辑，以及出售股票的数量的计算，
同时还会处理如果持有的股票小于计算出来的股票数量的情况。  
The `invest` method in the `BasicStrategy` class contains the logic for buying and selling stocks, including when to execute these actions as mentioned earlier. The `sharesToBuy` method includes the logic for buying stocks, which involves calculating the quantity to buy. Similarly, the `sharesToSell` method includes the logic for selling stocks and calculates the quantity to sell, while also handling situations where the number of stocks held is less than the calculated quantity.