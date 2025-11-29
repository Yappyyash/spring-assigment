# H2 Database Console - Quick Guide

## 🌐 Access the Console

Open your browser and go to: **http://localhost:8080/h2-console**

---

## ✅ Correct Connection Settings

When the H2 Console login page appears, use these exact settings:

| Field | Value |
|-------|-------|
| **Saved Settings** | Generic H2 (Embedded) |
| **Setting Name** | Generic H2 (Embedded) |
| **Driver Class** | `org.h2.Driver` |
| **JDBC URL** | `jdbc:h2:mem:interview_db` |
| **User Name** | `sa` |
| **Password** | (leave empty) |

---

## ❌ Common Mistake

**DO NOT** select "MySQL" or use `com.mysql.cj.jdbc.Driver`!

We switched from MySQL to H2 database, so you must use the H2 driver.

---

## 📊 Once Connected

After logging in, you can:

1. **View Tables**: Click on table names in the left panel
   - `AVAILABILITY`
   - `CANDIDATE`
   - `INTERVIEWER`
   - `INTERVIEW_SLOT`

2. **Run SQL Queries**: Use the SQL editor to query data
   ```sql
   SELECT * FROM INTERVIEWER;
   SELECT * FROM INTERVIEW_SLOT;
   SELECT * FROM CANDIDATE;
   ```

3. **See Data in Real-Time**: Any data you create via the API will appear here

---

## 🔄 Screenshot Guide

1. Open http://localhost:8080/h2-console
2. You'll see a login form
3. Make sure "Generic H2 (Embedded)" is selected in the dropdown
4. The Driver Class should automatically be: `org.h2.Driver`
5. Change JDBC URL to: `jdbc:h2:mem:interview_db`
6. Username: `sa`
7. Password: (empty)
8. Click "Connect"

---

## 💡 Troubleshooting

**Error: "Class com.mysql.cj.jdbc.Driver not found"**
- ✅ Solution: You selected MySQL driver by mistake. Select "Generic H2 (Embedded)" instead.

**Error: "Database not found"**
- ✅ Solution: Make sure the Spring Boot application is running first.
- ✅ Check the JDBC URL is exactly: `jdbc:h2:mem:interview_db`

**Can't connect**
- ✅ Make sure the server is running on port 8080
- ✅ Check http://localhost:8080/api/slots/available works first

---

## 🎯 Quick Test

After connecting to H2 Console, run this query to see if everything works:

```sql
SELECT COUNT(*) FROM INTERVIEWER;
```

If you get a result (even if it's 0), you're connected successfully!
