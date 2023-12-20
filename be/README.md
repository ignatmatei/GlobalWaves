# Proiect GlobalWaves  - Etapa 2

## Ignat Matei-Marius 325CA

### Am folosit scheletul oficial; copilot atunci cand aveam cod aproape duplicat: e.g. creasem metoda remove album si copilot a facut remove podcast; si cand am facut logica de verificare pentru a sterge un artist a completat logica de verificare pentru a sterge un host si cam astea ar fi singurele locuri.

#### design patterns folosite:

* **creationale**
    * **singleton** - pentru a crea o singura instanta a claselor `UserFactory` si `DeleteAuthorizer`
    * **factory** - pentru a crea obiecte de tip `User`
* **behaviorale**
  * **dependency injection** - pentru a injecta dependentele de logica tip pot sa sterg x in clasa `CommandRunner`

#### logica sistem de paginare
* pentru mine pagina e practic doar un string, iar userul retine pe ce tip de pagina se afla + numele artistului/host ului
daca se afla pe pagina acestuia.
* pentru a cauta artist/host tot searchbar-ul am folosit, a fost destul de usor sa se faca refactoring pe
sol oficiala)

Sa zicem ca ceva special ar fi faptul ca user ul retine podcast urile si albumele pe care le are, iar la afisare
am clase separate pentru output, dar chiar nu simt ca am facut ceva wow la aceasta tema.