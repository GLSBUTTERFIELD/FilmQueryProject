# FilmQueryProject
### Overview
This project models a video store database where users are prompted to input either a film id or keyword and are then shown information about the film(s) including: <ul><li>title</li><li>release year</li><li>language</li><li>rating</li><li>description</li><li>cast.</li></ul>

Users are then greeted with a submenu where they can go back to the main menu or choose to see additional information about each film, including:<ul><li>category</li><li>length</li><li>special features</li><li>film id</li><li>rental rate</li><li>rental duration</li><li>replacement cost</li><li>inventory list of the film along with the condition and store id.</li></ul>

If there are no films containing the given keyword or if the user enters an invalid response, they are prompted to enter a new keyword or film id until they choose to exit the program.

### Technologies & Concepts Used
<li>Object-Relational Mapping</li><ul>
	<li>MAMP</li>
	<li>MySQL</li>
	<li>driver implmentation</li>
	<li>database connection</li>
	<li>query definition and execution with bind variables</li>
	<li>processing results and closing connections</li></ul>
<li>SQL Queries</li><ul>
	<li>SELECT statements & alias names</li>
	<li>FROM, WHERE, & ORDER BY clauses</li>
	<li>LIKE operators & wildcards</li>
	<li>JOIN conditions & ON clauses</li>
	<li>associative tables</li>
	<li>primary & foreign keys</li></ul>
<li>Java & Object Oriented Design</li><ul>
	<li>Java object construction from MySQL data</li>
	<li>java.sql imports</li>
	<li>exceptions & try/catch blocks</li>
	<li>encapsulation, getters, & setters</li>
	<li>interfaces & inheritance</li>
	<li>polymorphism & overrides</li>
	<li>calling methods from different classes</li>
	<li>iterating over arraylists with for each loops</li>
	<li>while loops</li>
	<li>switches</li></ul>
<li>Eclipse</li><ul>
	<li>Maven dependencies</li>
	<li>keyboard shortcuts</li>
	<li>source menu commands</li></ul>
<li>Sublime</li>
<li>Git/GitHub</li>
<li>Unix CommandLine</li>
<li>MacOS Terminal</li>
</ul>

### Lessons Learned
#### Overall:
I really enjoyed this project and found it fun and rewarding putting many different applications together. I especially like writing SQL queries and determining the primary/foreign key relationships and the best way to retrieve the correct data. I tried to use the Debugger unsuccessfully and would like to spend more time learning that and writing JUnit tests in the future.
#### Challenges:
I was challenged this week by stretch goal #3, specifically getting the copies in inventory and their condition for multiple films (when a user searches by keyword and then chooses to see all film details). Unfortunately, the print out shows 0 for store id and null for media condition, even though it works for an individual film. I'm not sure if it has to do with my overloaded subMenuChoice() in the App class, where I pass a list of films instead of an individual film. I tried to troubleshoot in different ways like creating a findFilmAndInventoryByKeyword() but found myself getting confused and frustrated, and ended up commenting that out so that at least my code was working. 
I tried to create a separate method to construct a new Film in my DatabaseAccessorObject class to make my code more dry, but was running into exceptions so I kept it as is, with both findFilmById and findFilmByKeyword methods listing out the setters and getters to construct new films. 
#### Successes:
Fortunately I was able to complete a lot of the homework during class, even accidentally finishing some of the JOIN conditions during the lab. This set me up for success to tweak a lot of my code and work on the stretch goals. I was able to successfully implement the first and second goals and half of the third one, but as mentioned above, I was unable to get store ids and media conditions for multiple films. I played around a lot with the getInventoryCondition() and was happy that I was able to successfully call it from my findFilmById method.
I think I'm getting better at writing more concise code and breaking things into smaller methods, which are in charge of completing one job only. For example, I created separate methods for Case 1 and 2 of my switch, which helped condense things down.

