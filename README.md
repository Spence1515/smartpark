Java Development Kit (JDK): Make sure you have Java JDK 8 or later installed.
Maven: Ensure Maven is installed on your system.
Git: (Optional) To clone the repository.

git clone https://github.com/Spence1515/smartpark.git
git pull main
cd smartpark

Using IDE (e.g., IntelliJ IDEA, Eclipse):

Right-click project on Project Explorer then select Maven\Update Project then okay.
Clean and Build project.

Navigate to the src\main\java\hitachi\smartpark\SmartparkApplication.java file.
Right-click on Application.java and select Run.


API DOCUMENTATION
BASE URL
The default base URL is http://localhost:8080

API ENDPOINTS
Register Parking Lot: POST /api/v1/registerParkingLot
Register Vehicle: POST /api/v1/registerVehicle
Check-In Vehicle: PATCH /api/v1/checkin
Check-Out Vehicle: PATCH /api/v1/checkout
Get Available Parking Lots: GET /api/v1/available
View All Vehicles in a Parking Lot: GET /api/v1/in-lot/{parkingLotId}


DATABASE
The application uses an in-memory H2 database, which gets initialized at runtime. You can access the H2 console to view the database contents:

ACCESSING H2 CONSOLE
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdbspencer
Username: sa
Password: (leave it blank)

POSTMAN API COLLECTION
https://api.postman.com/collections/20867337-6cc74334-84c8-467e-a7cd-abb85b9df0e0?access_key=PMAT-01J5KY7W4Y2MS80FGFDSECDABA
