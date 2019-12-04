# Distributed Typosquatting Detector

Distributed Typosquatting Detector is the distributed application that scans domain squatting URLs in multiple remote machines. It has the simple web interface that receives a user input and then dynamically returns a scan result.

## Getting Started

This project is divided into the server and client program.

### Running the Server

#### Requirements

* Java Development Kit 11 or higher
* Apache Tomcat 9.0 or higher

#### Instructions

1. Clone this repository to the location where you wish to run the server program. Since this is a private repository at the moment, you will have to use the special command as follows.

    ```shell
    git clone https://your_user_name_here@github.com/msukmoon/typosquatting-detector
    ```

2. Copy and paste typosquatting-detector.war from the project directory to the webapps directory under your Tomcat home directory.
    > **Note:** You may need to use ``sudo`` to access the Tomcat subdirectories.

3. In the Tomcat directory, run ``bin/catalina.sh run`` to start the server. Enter the IP address to bind the server to.
    > **Note:** It is recommended that you start Tomcat from the command line and not as a service because the server requires input from the console to start up.

4. Go to [``localhost:8080/typosquatting-detector/search``](http://localhost:8080/typosquatting-detector/search) in a web browser. You should see the dashboard.

5. Enter a URL to search for typo domains and click Search or press enter.

6. To shut down the server, press Ctrl-C in the terminal where you started Tomcat.

### Running the Clients

> **Note:** You will have to **start the server first** before running the clients. You may run the client program in multiple machines.

#### Requirements

* Java Development Kit 11 or higher
* Google Chrome
* [ChromeDriver](https://chromedriver.chromium.org/downloads) that matches the version of your Chrome
* Xvfb (only for Linux environments)

#### Instructions

1. Clone this repository to the location where you wish to run the client program. Since this is a private repository at the moment, you will have to use the special command as follows.
    ```shell
    git clone https://your_user_name_here@github.com/msukmoon/typosquatting-detector
    ```

2. Go into the cloned directory.
    ```shell
    cd typosquatting-detector
    ```

3. Compile ``Client.java``, ``ClientImpl.java`` and ``Server.java`` with their dependent jar files in the lib directory.
    ```shell
    javac -cp lib/\* src/typosquatting_detector/Client.java src/typosquatting_detector/ClientImpl.java src/typosquatting_detector/Server.java
    ```

4. Run ``ClientImpl.class``.
    ```shell
    java -cp src:lib/\* typosquatting_detector.ClientImpl
    ```

5. The program will ask for your local IP address, hosted server's IP address and chrome driver path. To run the server and clients within the localhost, enter all IP addresses as ``127.0.0.1``. The chrome driver path is usually located at ``/usr/local/bin/chromedriver``.

#### Virtual Machine for Running the Client

The custom virtual machine appliance that is setup for running the client program is available at **[here](https://drive.google.com/file/d/1c9HYGMQfblpX-hK-a4s_RQNDVVug69aK/view?usp=sharing)**. It is Ubuntu 18.04.3 LTS that includes OpenJDK 11, Chrome, Chrome Web Driver for Selenium and Xvfb. The network adapter for the virtual machine should be attached to ``Bridged Adapter`` but  ``NAT`` or ``Host-Only Adapter``. The password is ``1234``. It is recommended to import and run this appliance in VirtualBox.

## Program Architecture and Description

> **Note:** We need to provide some **high level details** about the architecture of our project. We need to answer why we chose a specific way of doing it, compared to all other possible ways. **Delete this when we are done**.

(TBA: General overview. Why Java RMI? Why JSP?)
Used rmi so it is distributed.
JSP so we could write it all in java?

//Add more detail for these ^^
		              

### Server Program

(TBA: An overview of the server side code.)
The Server as a whole functions as the Master Node and the Web Dashboard. The Server is responsible for displaying the form and the results to the Web Dashboard, as well as taking user input and generating all typo squatting variants of a given URL. 

#### Servlet

(TBA)
This is our Web Dashboard. It displays our index.jsp file. Initiall, index.jsp contains a form where a user can enter a URL. When the user presses the button, the servlet gives the URL to the Server. Once the Server is finished assigning all the URL typos to the Worker Nodes, the servlet displays the results. 

#### ServletListener

(TBA)
This class waits for the servlet to be started. When the servlet is started, it starts running the server.

#### Server

(TBA)
This is an interface which is implemented by ServerImpl. It delcares(proper term?) the methods that need to be implemented by the Server in order to handle client connections, assigning URLs to clients, and receiving results back from clients. 

#### ServerImpl

(TBA)
This serves as the Master Node. It first receives a URL, and generates typo squatting variants of that URL, and paces them all in a queue. Then, it will assign one URL at a time to any running Worker Nodes to be crawled, and saves any results it receives back from the clients. It will then display the results on the Web Dashboard. 

#### AdjacentKeys

This class has 1 public method, which returns a Map, where each key is mapped to an array of keys which it is adjacent to on the keyboard. This map is used for generating character replacement typos and character insertion typos.

#### ReportGenerator

ReportGenerator class collects the reports received from the worker nodes. Each of the reports is consist of a url, a base64 encoded screenshot, and a source code. This class reads the reports from a folder, decodes the screenshots, and saves them into another folder. Then it assembles all the alive URL variants, along with corresponding screenshot and a source code, and creates a single html file (report.html).

### Client Program

(TBA: An overview of the client side code.)
The Client as a whole serves as the Worker Node. When run, it connects to the server, and reports itself to the Master Node, then waits to be assigned a URL to crawl. Once it receives a URL from the Master Node, it uses headless chrome to check if that URL exists, and if it does, take a screenshot and collect the html code, then report all of this back to the Master Node. 

#### Client

(TBA)
This is an interface which is implemented by ClientImpl. It declares the crawl() method, which the Client must implement in order to crawl URLs.

#### ClientImpl

(TBA)
This serves as the Worker Node. It reports itself for duty to the Master Node, and waits for the Master Node to assign it a URL to crawl. Once it receives the URL, it checks if the page exists. If the typosquatting domain is alive, it crawls the url using headless chrome, collects the html code, and takes a screenshot of the page. Finally, it will report the html code and screenshot (encoded in base64) back to the Master Node in .txt format.
The Worker Node takes a screenshot in a string format so that each url corresponds to a single file containing image and page source. Concatenating the elements in a single file also facilitates the management of files in both server and client sides.


### Third-Party Resources

(TBA)
Tomcat?
rmi?
headless chrome?



## Authors

We are team 'Unnamed' at Stony Brook University's Fall 2019 CSE 331 class.

* **Henry Crain** - [henrycrain](https://github.com/henrycrain) - henry.crain@stonybrook.edu
	* Wrote algorithm for generating typos using the typo-generation model #4 from the Section 3.1 of this [paper](https://www.usenix.org/legacy/event/sruti06/tech/full_papers/wang/wang.pdf)
	* (TBA)
* **Hye-Jun Jeong** - [HyejunJeong](https://github.com/HyejunJeong) - hye-jun.jeong@stonybrook.edu
	* Wrote algorithms for generating typos using the typo-generation models #1 and  #2 from the Section 3.1 of this [paper](https://www.usenix.org/legacy/event/sruti06/tech/full_papers/wang/wang.pdf)
	* Crawled typo URLs and get a page source and a screenshot.  
	* Transferred resulting images and HTML scripts from the clients to the server.  
	* Generated old and new results (report.html) to the web dashboard.  
* **Myungsuk (Jay) Moon** - [msukmoon](https://github.com/msukmoon) - myungsuk.moon@stonybrook.edu
	* Wrote algorithm for generating typos using the typo-generation model #3 from the Section 3.1 of this [paper](https://www.usenix.org/legacy/event/sruti06/tech/full_papers/wang/wang.pdf)
	* Made the web interface by using the JavaServer Pages (JSP). Received the user input and then dynamically returned the result.
	* Made the distributed system by using Java Remote Method Invocation (Java RMI). Registered and deregistered clients using their uniquely generated IDs on the server. Made the queue of URLs on the server to be remotely accessed by multiple clients.
	* Set up the VirtualBox appliance for running the clients.
* **Nicholas Reimer** - [nreimer](https://github.com/nreimer) - nicholas.reimer@stonybrook.edu
	* Wrote algorithm for generating typos using the typo-generation model #5 from the Section 3.1 of this [paper](https://www.usenix.org/legacy/event/sruti06/tech/full_papers/wang/wang.pdf)
	* (TBA)
