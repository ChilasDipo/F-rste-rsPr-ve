# UC01 - Bestil af en New Yorker Væg


|  Use Case Section | Comment | 
| ------------------- | ------------ | 
| Use Case name | UC01 Bestil af en New Yorker Væg | 
| Scope| NewYorker App | 
| Level | Brugermål | 
| Primary Actor | Tømre/Sælger | 
| Stakeholder And Interest |	Tømreren ønsker at kunne designe en væg ud fra kundens behov, findeprisen på væggen og sende den til NewYorker.| 
| Precondictions | N/A | 
| Success Guarantee | Væggen er blevet designet, prissat og sendt til NewYorker | 
| Main Success Scenario  | <ol><li>Tømreren ønsker at designe en new yorker væg.</li><li>Systemet anmoder om længde og højde på væggen.</li><li>Tømreren angiver længde og højde på væggen</li><li>Systemet beregner antal fag og antal glas og presentere det for Tømreren.</li><li>Tømreren bekræfter antal fag og antal glas</li><li>Systemet opstiller designet af væggen.</li><li>Systemet beder database om prisen per glassfelt</li><li>Systemet ganger prisen per glas med antal glas</li><li>System præsenterer prisen for tømreren</li><li>Tømreren klikker på send ordre</li><li>Systemet tager alt information om væggen og sender det med mail til NewYorker</li></ol>|
| Extensions| 3a. Der kommer en fejlbesked pga. indtastningfejl i enhederne. <br> 3b. Højden støder på maksimum og kommer med fejlbesked. <br> 3c. Højde og/eller længde er for kort og systemet kommer med en fejlbesked. <br> 4a. Systemet viser en fejlbesked, hvis antal af glass eller fag ikke kan udregnes korrekt. <br> 5a <ol><li>Systemet anmoder om eventuelle extra "ting" til væggen</li><li>Tømreren angiver alle extra "ting" til væggen</li><li>Systemet beder databasen om prisen på glas og tillæg for de ”extra ting” som tømreren har valgt.</li><li>Systemet lægger priserne for alle tillæg sammen med prisen for alt glasset.</li></ol> |
| Techology and Data Variacations List |   
