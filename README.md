# **Indian Night Event Management System**

## Project Overview

**The Indian Night is an annual event that brings people together to celebrate and enjoy various activities. This project aims to create a web platform where users can attend the event and purchase tickets. The platform will have different roles with specific permissions and functionalities.** <br>

### Roles and Permissions

### <u> Admin :  </u>

**Login:** Admin can log in using JWT authentication. 

**User Management:** Admin can manage users, including setting permissions.

**Event Management:** Admin can create and manage events with the following details:

    Title
    Description
    Price
    Location
    Images
    Start Date
    End Date
    Number of Attendees
    Table of Questions for Attendees
    Events are hidden by default, with an option to make them live.

**Reservation Management:** Organizer can accept or refuse reservations.

**Blog Management:** Admin can manage blog posts.
* `````   NB _Events are hidden by default, with an option to make them live._`````

### <u>Organisateur (Organizer) : </u>

**Login:** Organizer can log in using JWT, Google, or Facebook authentication.

**Reservation Management:** Organizer can accept or refuse reservations.

### <u>User : </u>

**Login:** User can log in using JWT, Google, or Facebook authentication.

**Reservation:** User can make reservations for the event.

**Ticket:** User can get a PDF ticket and receive it via email.

**Profile Management:** User can create and edit their profile, which includes:

    Image
    Bio
    Hobby (type of gear or material used in hobby)
    Location

**Blog Interaction:** User can like and comment on blog posts.