- DreamPortal - UI Automation Project

  Automated functional testing for the DreamPortal website using Java, Selenium, and JUnit.

- Project Objective

  Automate and validate the following pages from the DreamPortal site:

- index.html – Home Page
  - Loading animation appears and disappears (~3s)
  - "My Dreams" button becomes visible
  - Clicking button opens:
    - `dreams-diary.html`
    - `dreams-total.html` in new tabs/windows

- dreams-diary.html – Dream Log Table
  - Total entries: 10
  - Dream types: only "Good" or "Bad"
  - Each row contains: Dream Name, Days Ago, Dream Type

- dreams-total.html – Summary Page
  - Good Dreams: 6
  - Bad Dreams: 4
  - Total Dreams: 10
  - Recurring Dreams: 2:
   - "Flying over mountains"
   - "Lost in maze"

- Tech Stack
  - Language: Java  
  - Automation Tool: Selenium WebDriver  
  - Testing Framework: JUnit  
  - IDE: Eclipse

- Project Structure

  └── src/
  └── test/
  └── java/
  └── DreamPortal/
  └── ChromeNew.java

- ChromeNew.java:
  This file contains all the Selenium test logic for automating and validating the three pages:
  index.html, dreams-diary.html, and dreams-total.html.

- Run Instructions
  1. Clone this private repository
  2. Open in Eclipse
  3. Run `ChromeNew.java`
 
---

- Sample Test Output

Below is a sample console output from a successful automation test run of the DreamPortal project:

Clicked the button successfully!
Rows found: 10
10 rows found.
Row 1 has 3 columns.
Row 2 has 3 columns.
Row 3 has 3 columns.
Row 4 has 3 columns.
Row 5 has 3 columns.
Row 6 has 3 columns.
Row 7 has 3 columns.
Row 8 has 3 columns.
Row 9 has 3 columns.
Row 10 has 3 columns.
Total Good dreams: 6
Total Bad dreams: 4
Recurring Dreams Found: [Flying over mountains, Lost in maze]
Good Dreams: 6
Bad Dreams: 4
Total Dreams: 10
Dreams This Week: 7
Recurring Dreams: 2
Expected recurring dream present: Flying over mountains
Expected recurring dream present: Lost in maze


---

- This output confirms:
  - All validations on table structure and values passed
  - Summary page values match expected data
  - Recurring dreams are correctly identified


