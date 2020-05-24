# Happy Moments

## Funktionalität

- Benutzer sollen sich registrieren können -> fällt eventuell erstmal weg, da es ein in sich geschlossenes System sein soll (nicht für andere)
- Benutzer sollen sich anmelden können
- Benutzer sollen ihre Daten ändern können
- Benutzer sollen neue Momente erstellen können
- Benutzer sollen ihre Momente mit anderen Benutzern teilen können
    - Einladung neuer Benutzer über E-Mail -> E-Mail-Versand
- Benutzer sollen zu ihren eigenen und den mit ihnen geteilten Momenten Dateien hinzufügen können
- Benutzer sollen ihre hochgeladenen Dateien löschen können
- Die Ersteller eines Moments sollen alle zugehörigen Dateien löschen können



## Datenbank

### Benutzerverwaltung

- user
    - id: UUID
    - username: varchar
    - email: varchar
    - password: hash

    
### Moments

- moments
    - id: UUID
    - name: varchar
    - description: varchar
    - date: date
    - owner: FK user.id
    
    
- moments_user
    - moments_id: FK moments.id
    - user_id: FK user.id


### Dateien

- files
    - id: UUID
    - description: varchar
    - date: date
    - upload_date: date
    - file: blob
    - user_id: FK user.id
    - moments_id: FK moments.id

