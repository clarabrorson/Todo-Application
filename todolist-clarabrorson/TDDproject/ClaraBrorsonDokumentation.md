# Projekt TODO

## Clara Brorson
Testkört av: Marcus Medina

### Beskrivning
Projektet är en Todo - applikation som använder en mondoDB databas för att lagra hantera todos. Projektet består av olika klasser som ansvarar för olika områden av programmet. MongoDBFacade klassen hanterar exempelvis anslutningen till databasen samt innehåller metoder för att utföra CRUD - operationer. KeyReader klassen har hand om anslutningssträngen till databasen, om anslutningssträngen inte kan lokaliseras ansluter den till en lokal databas.

Todo klassen kan beskrivas som en modell för en todo, den innehåller dess egenskaper samt metoder. Klassen TodoTests innehåller enhetstest som testar metoderna i Todo klassen, samt mockning av databasen. 

Menu klassen hanterar menyn i programmet. Den skapar en instans av Apllication klassen och anropar dess metoder. Application klassen ansvarar för logiken och använder sig av MongoDBFacade för att utföra CRUD - operationer. Den hanterar inmatning från användaren. 

## Planering
Jag skapade en backlog på Github och delade inte arbetet i klasser. Syftet var att få en grundläggande bild av projektet och arbeta utefter den. 

[TODO Backlog](https://github.com/orgs/Campus-Molndal-JIN23/projects/49)

## Arbetet och dess genomförande
Jag började arbetet med att gå igenom uppgiften och dess krav och arbetade sedan fram en struktur för projektet. Jag skapade backlogen i form av en kanban board för att planera och ordna projektets uppbyggnad, samt för att lätt kunna följa processen av projektet.

När jag hade strukturerat upp projektet i klasser, bestämt databas och försett projektet med nödvändiga beroenden, började jag implementera MongoDBFacade klassen och KeyReader klassen för att försäkra mig om att anslutningen fungerade. Vidare fokuserade jag på Todo klassen samt tillhörande TodoTests klassen. Senare kom CRUD - operationerna och tillsist menysystemet. 

Utmaningen i detta projekt var bland annat anslutningen till databasen, som till en början inte fungerade. En annan svårighet var att skapa mig en bild av den övergripande logiken som projektet kräver, samt hur CRUD-operationer och menysystemet samverkar. 

När jag skulle lagra exempel-todos i databasen fick jag en parameterkonflikt. Eftersom MongoDB automatiskt tillhandahåller ett id, försökte jag lagra todos utan att ange id - parametern. Detta medförde dock en parameterkonflikt där programmet tolkade en boolean för en sträng. Jag hade kunnat lösa det genom att ändra plats på parametrarna i todo klassen och dess konstruktor men det krävde också ändringar i andra delar av projektet. Jag löste det istället genom att själv ange id för todon. 

## Reflektion & Slutsatser
Jag har fått en fördjupad kunskap av denna termins innehåll, och hur samverkan och integrationen ser ut mellan mellan olika system i ett projekt. 

Jag har också fått en större insikt i vilka mina utmaningar är, vilket gör att jag blir medveten om vad jag behöver öva på samt hur. Den återkommande svårigheten för mig är strukturen och planeringen av projekt. I början av processen har jag ofta svårt att få till en helhets bild av projektet och dess logik. Detta gör att det ibland är svårt att komma igång. Under arbetets gång brukar dock denna bild klarna och växa fram. Jag har förstått att övning ger färdighet, och att det blir lättare med ökad kunskap och erfarenhet. 

