# 🎯 Interview Scheduler - CRUD UI Guide

## 🚀 Quick Start

Your beautiful CRUD UI is now live at: **http://localhost:8080**

---

## ✨ Features

### 📊 **Dashboard**
- Real-time statistics showing:
  - Total Interviewers
  - Total Candidates  
  - Total Slots
  - Available Slots
- Server status indicator

### 👨‍💼 **Interviewers Tab**
**Create:**
- Add interviewer name, email, and max interviews per week
- Click "Add Interviewer"

**View:**
- See all interviewers in a table
- View their details and limits

**Delete:**
- Remove interviewers with one click

### 👥 **Candidates Tab**
**Create:**
- Add candidate name and email
- Click "Add Candidate"

**View:**
- See all candidates in a table

**Delete:**
- Remove candidates easily

### 📅 **Availability Tab**
**Set Availability:**
1. Select an interviewer
2. Choose day of week
3. Set start and end time
4. Click "Set Availability & Generate Slots"

**What Happens:**
- System automatically generates interview slots for the next 2 weeks
- Slots appear in the "Interview Slots" tab
- Based on the day and time you specified

### 🎫 **Interview Slots Tab**
**View All Slots:**
- See all generated slots
- Filter by status (Available/Booked)
- View interviewer, date, time, and candidate info

**Refresh:**
- Click refresh to see latest slots

### 📝 **Bookings Tab**
**Book a Slot:**
1. Select a candidate
2. Choose an available slot
3. Click "Book Slot"

**Reschedule:**
1. Select the candidate
2. Enter current slot ID
3. Choose new slot
4. Click "Reschedule"

**View Booked Interviews:**
- See all confirmed bookings
- Quick reschedule option

---

## 🎨 UI Features

✅ **Modern Design** - Beautiful gradient backgrounds and smooth animations  
✅ **Responsive** - Works on all screen sizes  
✅ **Real-time Updates** - Server status checks every 5 seconds  
✅ **User-Friendly** - Clear labels, helpful messages, and intuitive layout  
✅ **Color-Coded** - Status badges for easy identification  
✅ **Smooth Animations** - Fade-ins, slide-ups, and hover effects  

---

## 📖 Usage Flow

### **Complete Workflow Example:**

1. **Add an Interviewer**
   - Go to "Interviewers" tab
   - Fill in: Name: "John Doe", Email: "john@example.com", Max: 5
   - Click "Add Interviewer"
   - ✅ Interviewer created with ID 1

2. **Set Availability**
   - Go to "Availability" tab
   - Select "John Doe" from dropdown
   - Choose "MONDAY", Time: 10:00 - 11:00
   - Click "Set Availability & Generate Slots"
   - ✅ System generates slots for all Mondays in next 2 weeks

3. **Add a Candidate**
   - Go to "Candidates" tab
   - Fill in: Name: "Jane Smith", Email: "jane@example.com"
   - Click "Add Candidate"
   - ✅ Candidate created with ID 1

4. **View Available Slots**
   - Go to "Interview Slots" tab
   - See all generated Monday 10-11 AM slots
   - Note the slot IDs

5. **Book an Interview**
   - Go to "Bookings" tab
   - Select "Jane Smith" from candidate dropdown
   - Select a slot from available slots dropdown
   - Click "Book Slot"
   - ✅ Interview booked!

6. **Reschedule (if needed)**
   - In "Bookings" tab, click "Reschedule" on a booked interview
   - Or manually enter old slot ID
   - Select new slot
   - Click "Reschedule"
   - ✅ Interview moved to new time!

---

## 🎯 Tips

💡 **Dashboard Stats** - Always visible at the top, updates automatically  
💡 **Color Coding** - Green = Available, Red = Booked  
💡 **Alerts** - Success messages appear at top, auto-dismiss after 5 seconds  
💡 **Server Status** - Green dot = Online, Red dot = Offline  
💡 **Dropdowns** - Auto-populate with available data  

---

## 🔧 Technical Details

**Frontend:**
- Pure HTML/CSS/JavaScript
- No framework dependencies
- Responsive design
- Modern animations

**Backend Integration:**
- RESTful API calls
- Real-time data fetching
- Error handling
- Automatic refresh

**Endpoints Used:**
- `GET /api/interviewers` - List all interviewers
- `POST /api/interviewers` - Create interviewer
- `GET /api/candidates` - List all candidates
- `POST /api/candidates` - Create candidate
- `POST /api/interviewers/{id}/availability` - Set availability
- `GET /api/slots/available` - Get all slots
- `POST /api/slots/{id}/book` - Book a slot
- `PUT /api/slots/reschedule` - Reschedule interview

---

## 🎨 Design Highlights

- **Gradient Backgrounds** - Purple to blue gradient
- **Card-Based Layout** - Clean, organized sections
- **Smooth Transitions** - Hover effects and animations
- **Modern Typography** - Segoe UI font family
- **Status Indicators** - Pulsing dots and badges
- **Responsive Tables** - Scrollable on small screens
- **Action Buttons** - Color-coded by function

---

## 🚀 Next Steps

1. Open http://localhost:8080 in your browser
2. Start by adding an interviewer
3. Set their availability
4. Add candidates
5. Book interviews!

Enjoy your beautiful Interview Scheduler! 🎉
