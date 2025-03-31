---
layout: page
title: User Guide
---

<div style="display: flex; justify-content: center; align-items: center;">
<span class="parisienne-regular" style="font-size: 3em; font-weight: bold;">
  AcademySource User Guide
</span>
</div>

**AcademySource** is a streamlined contact management **desktop app built for use via command-line interface (CLI)**, designed to help students stay connected with their academic network. It centralizes key contact details of professors and teaching assistants,
removing the need to dig through emails or portals. With its simple and efficient CLI-based design, AcademySource lets you manage academic contacts quickly and effortlessly—so you can stay organized and focused on your learning journey.

With AcademySource, you can:
* Store and manage contact information of professors and TAs (e.g., email, phone number, role, tags, module)
* Search for contacts by name, contact details, role, and/or module code
* Mark important contacts as favorites for quick access
  
--------------------------------------------------------------------------------------------------------------------

## Table of Content
- [Quick start](#quick-start)
- [Graphic User Interface Layout](#graphic-user-interface-layout)
- <details>
    <summary>
        <a href="#features">Features</a>
    </summary>
    <a href="#prefix-table"> - Prefix table <br></a>
    <a href="#viewing-help--help"> - Viewing help <br></a>
    <a href="#listing-all-persons--list"> - Listing all persons <br></a>
    <a href="#adding-a-person--add"> - Adding a person <br></a>
    <a href="#editing-a-person--edit"> - Editing a person <br></a>
    <a href="#locating-persons-by-name-phone-module-and-favourites--find"> - Locating persons by name, phone, module and favourite <br></a>
    <a href="#mark-un-mark-a-person-as-favourite--fav"> - Mark / un-mark a person as favourite <br></a>
    <a href="#deleting-a-person--delete"> - Deleting a person <br></a>
    <a href="#clearing-all-entries--clear"> - Clearing all entries <br></a>
    <a href="#exiting-academysource--exit"> - Exiting AcademySource <br></a>
    <a href="#saving-the-data"> - Saving the data <br></a>
    <a href="#editing-the-data-file"> - Editing the data file <br></a>
    <a href="#archiving-data-files"> - Archiving data files (coming soon) <br></a>

  </details>
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)
- [Glossary](#glossary)

## Quick start

1. Ensure you have `Java 17` or above installed on your computer. Follow the steps below to check if you already have `Java 17` installed:
   2. Navigate to your operating system's terminal:
      <details>
          <summary><strong>For Windows:</strong></summary>
              <ol>
                  <li>Hold down Windows button and R (⊞ Win + R)</li>
                  <li>Type <code>cmd</code> and press enter</li>
              </ol>
      </details>
      <details>
          <summary><strong>For MacOS:</strong></summary>
              <ol>
                  <li>Click the Launchpad icon in the Dock, type <code>Terminal</code> in the search field, then click Terminal, OR</li>
                  <li>In the Finder, open the <code>/Applications/Utilities</code> folder, then double-click Terminal.</li>
              </ol>
      </details>
      
   3. Type <code>java -version</code> and press enter. 
   4. If you have `Java 17` installed, the terminal should look like:
       <details>
           <summary><strong>On Windows:</strong></summary>
               <pre><code>
       C:\Users\UserName>java -version
       java version "17.0.12" 2024-07-16 LTS
       Java(TM) SE Runtime Environment (build 17.0.12+8-LTS-286)
       Java HotSpot(TM) 64-Bit Server VM (build 17.0.12+8-LTS-286, mixed mode, sharing)
               </code></pre>
       </details>
       <details>
           <summary><strong>On MacOS:</strong></summary>
               <pre><code>
      user@username-MacBook-Air-3 ~ % java -version
      openjdk version "17.0.11" 2024-04-16 LTS
      OpenJDK Runtime Environment Zulu17.50+19-CA (build 17.0.11+9-LTS)
      OpenJDK 64-Bit Server VM Zulu17.50+19-CA (build 17.0.11+9-LTS, mixed mode, sharing)
               </code></pre>
       </details>
   

2. If you have `Java 17` installed, proceed to Step 3. If not, follow these instructions:
   3. Download `Java 17` from [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). 
   4. Select the installation package based on your Operating System. 
   5. Follow the instruction guide to install Java on your device. For more information, click [here](https://docs.oracle.com/en/java/javase/23/install/overview-jdk-installation.html).
   6. **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).  


3. Download the latest version of AcademySource from [here](https://github.com/AY2425S2-CS2103T-T17-4/tp/releases/tag/v1.3). Select `academysource.jar` to begin the download.


4. Copy the file to the folder you want to use as the _home folder_ for AcademySource. Note: This will create additional files required for AcademySource in your _home folder_.


5. Open AcademySource by double-clicking the program file, `academysource.jar`. Alternatively, you may type `java -jar academysource.jar` into your [terminal](#quick-start) and press enter.  
   **What you'll see:**   
   <details>
        <summary><strong>On Windows:</strong></summary>
            <img src="images/BootGuiWindows.png" width="700" onclick="openModal(this)"/>
   </details>
   <details>
        <summary><strong>On Mac OS:</strong></summary>
            <img src="images/BootGui.png" width="700" onclick="openModal(this)"/>
   </details>


6. Type a command in the command box and press Enter to execute it. For example, typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com r/TA m/CS2103T` : Adds a contact named John Doe to AcademySource with the phone number 98765432, email address johnd@example.com, role of TA and module CS2103T.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.


7. Refer to the [Features](#features) below for details of each command.
<br><br>
[Back to top](#table-of-content)
--------------------------------------------------------------------------------------------------------------------
## Graphic User Interface Layout

![Module Page](https://github.com/user-attachments/assets/b60b3f57-c899-435b-b07f-8ffcd3cdb34b)
![Contacts Page](https://github.com/user-attachments/assets/16145b59-b771-4418-8859-b1cdfca36604)


Basic features:
1. Menu Bar: A top menu bar which provides access to various functions within AcademySource.
2. Command Box: An input with a placeholder "Enter Command Here..." to enter commands into.
3. Result Display: A rectangular display box to display success message upon successful command execution, or error message upon failure.
4. Side Navigation: Navigates between Modules and Contacts tab.

Modules Page:
1. Module Files: A folder that stores all contacts related to the module code.
   Upon pressing, AcademySource will be directed to contacts page which displays contacts that matches the module code.

Contacts Page:
1. Contact List: A list of contacts stored inside AcademySource. Each row stores a contact name card with their relevant details.
2. Role: The role which each contact is assigned to. TA will be displayed as yellow bookmarker while Professor will be displayed as orange bookmarker.
3. Favourite Mark: A star symbol which indicate a contact is marked as favourite by you.

[Back to top](#table-of-content)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<a id="prefix-table"></a>
### Prefix Table [🔝](#table-of-content)

| **Prefix** | **Meaning**                | **Usage Example**     | **Remarks**                                                           |
|------------|----------------------------|-----------------------|-----------------------------------------------------------------------|
| `n/`       | Name                       | `n/John Doe`          |                                                                       |
| `p/`       | Phone number               | `p/98765432`          |                                                                       |
| `e/`       | Email address              | `e/johnd@example.com` |                                                                       |
| `r/`       | Role (`ta` or `prof`)      | `r/TA` or `r/PROF`    | Not used in `find`.                                                   |
| `m/`       | Module (can have multiple) | `m/CS2103T m/CS2101`  | Used differently in `find`. For multiple modules, `m/CS2103T CS2106`. |
| `f/`       | Favourite                  | `f/y` or `f/n`        | Not used in `add` or `edit`.                                          |

<a id="viewing-help--help"></a>
### Viewing help : `help` [🔝](#table-of-content)

Shows a message explaning how to access the help page.

<img src="images/helpMessage.png" width="956" onclick="openModal(this)"/>

Format: `help`

<a id="listing-all-persons--list"></a>
### Listing all persons : `list` [🔝](#table-of-content)

Shows a list of all contacts in AcademySource.

Before command:

<img src="images/list_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/list_after.png" width="500" onclick="openModal(this)"/>

Format: `list`

<a id="adding-a-person--add"></a>
### Adding a person: `add` [🔝](#table-of-content)

Adds a contact to AcademySource.

Before command:

<img src="images/add_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/add_after.png" width="500" onclick="openModal(this)"/>

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROLE [t/TAG] m/MODULE [m/MODULE]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

* `ROLE` must be either `ta` or `prof` (Case-insensitive, which means `TA` or `prof` are also valid).

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/prof`
* `add n/Betsy Crowe e/betsycrowe@example.com p/1234567 r/TA m/CS2103T`

<a id="editing-a-person--edit"></a>
### Editing a person : `edit` [🔝](#table-of-content)

Edits an existing contact in AcademySource.

Before command:

<img src="images/edit_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/edit_after.png" width="500" onclick="openModal(this)"/>

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG] [m/MODULE] [m/MORE_MODULES]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Roles can not be edited.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* When editing modules, the existing modules will be replaced by the new modules.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 m/CS2103T m/CS2106` Edits the module of the 2nd person to be `CS2103T` and `CS2106`.

<a id="locating-persons-by-name-phone-module-and-favourites--find"></a>
### Locating persons by name, phone, module, and favourites: `find` [🔝](#table-of-content)

Finds persons whose names contain any of the given keywords.

Before command:

<img src="images/find_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/find_after.png" width="500" onclick="openModal(this)"/>

Format: `find [n/NAME_KEYWORDS] [p/PHONE_KEYWORDS] [m/MODULE_KEYWORDS] [f/FAVOURITE_STATUS]`

- `n/` — Matches names using **case-insensitive, partial matches**.
- `p/` — Matches phone numbers using **partial matches**.
- `m/` — Matches module codes using **case-insensitive, partial matches**.
- `f/` — Filters by favourite status. Accepts only:
    - `y` → Favourite
    - `n` → Not favourite

#### 🔎 Search Behavior

- Keywords are **case-insensitive** for `name`, `phone`, and `module`.
- Name keyword **order does not matter**. For example, `Hans Bo` matches `Bo Hans`.
- Supports **partial keyword matching** for name, phone, and module.
- Only **one instance** of each prefix is allowed. Repeating a prefix is **not permitted**.
- All specified prefixes must match (**AND keywords**) for a person to be included in the results.

---

#### ✅ Valid Examples

| Command             | Description                                                                     |  
|---------------------|---------------------------------------------------------------------------------|
| `find n/John`       | Finds persons with names matching `John`, e.g., `John Doe`.                     |
| `find n/alex david` | Finds persons with names matching either `alex` or `david`, e.g., `Alex Yeoh`, `David Li`. |
| `find p/9123`       | Finds persons whose phone numbers contain `9123`. e.g. `91234567`               |
| `find m/2103`       | Finds persons with module codes like "CS2103T".                                 |
| `find f/y`          | Finds persons marked as favourites.                                             |
| `find f/n`          | Finds persons who are not marked as favourites.                                 |
| `find n/John m/CS2103T` | Finds persons whose name matches "John" **and** who are in the module "CS2103T".|
| `find m/CS2103 f/y` | Finds persons whose module matches `CS2103` **and** who are marked as favourites. |             

---

#### ❌ Invalid Examples

| Command | Reason |
|--------|--------|
| `find` | No search prefixes provided. |
| `find n/John n/Doe` | Duplicate `n/` prefix is not allowed. |
| `find f/maybe` | Invalid value for `f/`. Only `y` or `n` are allowed. |

---

<a id="mark-un-mark-a-person-as-favourite--fav"></a>
### Mark / un-mark a person as favourite : `fav` [🔝](#table-of-content)

Mark a specific person as favourite, or un-mark a person from favourite if that person is
already marked as favourite.
A star will be shown beside the person's name if it is marked as favourite.

Before command:

<img src="images/fav_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/fav_after.png" width="500" onclick="openModal(this)"/>

Format: `fav INDEX`

* Toggles the favourite status of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* the index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `fav 2` marks the 2nd contact in AcademySource as favourite.
* `fav 2` again un-marks the person.

<a id="deleting-a-person--delete"></a>
### Deleting a person : `delete` [🔝](#table-of-content)

Deletes the specified contact from AcademySource.

Before command:

<img src="images/delete_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/delete_after.png" width="500" onclick="openModal(this)"/>

Format: `delete INDEX [MORE INDEX]`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Index should not repeat in the same command.

Examples:
* `list` followed by `delete 2` deletes the 2nd contact from the result of the `list` command.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.
* `delete 1 2 3` deletes the first 3 contacts, given that all indexes exist within the contact list.

<a id="clearing-all-entries--clear"></a>
### Clearing all entries : `clear` [🔝](#table-of-content)

Clears all entries from AcademySource.

Before command:

<img src="images/clear_before.png" width="500" onclick="openModal(this)"/>

After command:

<img src="images/clear_after.png" width="500" onclick="openModal(this)"/>

Format: `clear`

<a id="exiting-academysource--exit"></a>
### Exiting AcademySource : `exit` [🔝](#table-of-content)

Exits AcademySource.

Format: `exit`

<a id="saving-the-data"></a>
### Saving the data [🔝](#table-of-content)

AcademySource data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<a id="editing-the-data-file"></a>
### Editing the data file [🔝](#table-of-content)

AcademySource data are saved automatically as a JSON file `[JAR file location]/data/academysource.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AcademySource will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause AcademySource to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

<a id="archiving-data-files"></a>
### Archiving data files `[coming in v2.0]` [🔝](#table-of-content)

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AcademySource home folder.

[back to top](#table-of-content)

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

[back to top](#table-of-content)

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                     |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROLE [t/TAG] m/MODULE…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com r/TA t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                              |
| **Delete** | `delete INDEX…​` <br> e.g., `delete 1` `delete 2 3`                                                                                                  |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                      |
| **Find**   | `find [n/NAME_KEYWORDS] [p/PHONE_KEYWORDS] [m/MODULE_KEYWORDS] [f/FAVOURITE_STATUS]` <br> e.g., `find n/James p/98765432 m/CS2106 f/y`               |
| **List**   | `list`                                                                                                                                               |
| **Help**   | `help`                                                                                                                                               |

[back to top](#table-of-content)

--------------------------------------------------------------------------------------------------------------------
## Glossary

| Term           | Definition                                                                                                                                                                     |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **CLI**        | `Command Line Interface; a way to interact with the application using` <br> `text-based commands`                                                                              |
| **Index**      | `A positive integer that refers to a contact's position in the displayed list`                                                                                                 |
| **JDK**        | `Java Development Kit; a software development environment required to run and develop Java` <br> `applications like AcademySource. Version 17 or above is needed`              |
| **Parameters** | `Values supplied by the user in commands (e.g., name, phone, module) that determine the` <br> `action taken by AcademySource. Often prefixed with identifiers like n/, p/, m/` |
| **Field**      | `A specific piece of information in a contact entry, such as name, phone number, email, role, or module`                                                                       |

[back to top](#table-of-content)

