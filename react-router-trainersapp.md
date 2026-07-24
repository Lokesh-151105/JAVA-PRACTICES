# React Hands-on Lab — TrainersApp (React Router)

## Objectives

- Explain the need and benefits of React Router
- Identify the components in React Router
- List the types of Router components
- Parameter passing via URL

## Concepts

### Need and Benefits of React Router
By default, a React SPA renders everything on a single page with no distinct URLs for different views. **React Router** is a library that adds client-side routing to a React app, allowing different components to be rendered based on the URL — without a full page reload.

**Benefits:**
- Enables multi-view navigation while keeping the SPA experience (no full page reloads)
- Bookmarkable, shareable URLs for specific views (e.g., `/trainers`, `/trainers/3`)
- Supports browser back/forward navigation naturally
- Allows passing data via URL parameters (e.g., an ID to show a specific record's details)
- Keeps the app organized into route-based components

### Components in React Router
| Component/Hook | Purpose |
|---|---|
| `BrowserRouter` | Wraps the app and enables routing using the HTML5 History API |
| `Routes` | Container that holds all `Route` definitions and renders the first matching one |
| `Route` | Maps a URL path to a component to render |
| `Link` | Renders a navigable anchor (`<a>`) without causing a full page reload |
| `NavLink` | Like `Link`, but can apply active styling based on the current route |
| `useParams` | Hook to read dynamic parameters (e.g., `:id`) from the current URL |
| `useNavigate` | Hook to programmatically navigate to another route |

### Types of Router Components
1. **`BrowserRouter`** – uses standard URL paths (e.g., `/trainers`); requires server configuration for deep links in production.
2. **`HashRouter`** – uses a hash portion of the URL (e.g., `/#/trainers`); works without server configuration since the hash isn't sent to the server.
3. **`MemoryRouter`** – keeps the routing history in memory (not in the URL); mainly used for testing or non-browser environments.

### Parameter Passing via URL
React Router allows dynamic segments in a route path using a colon prefix, e.g.:
```jsx
<Route path="/trainers/:id" element={<TrainerDetail />} />
```
Inside the target component, the `useParams()` hook retrieves the value:
```jsx
const { id } = useParams();
```
This `id` can then be used to look up specific data (e.g., a trainer's record) to display.

---

## Hands-on: Trainers Directory — "TrainersApp"

**Scenario:** Cognizant Academy wants an SPA to maintain a list of trainers and their expertise, with fields: T-ID, Name, Phone, Email, Stream, and Skills.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app TrainersApp
```

**2. Open the project in VS Code**
```bash
cd TrainersApp
code .
```

**3. Create `Trainer.js` — the Trainer class**
```jsx
class Trainer {
  constructor(TrainerId, Name, Email, Phone, Technology, Skills) {
    this.TrainerId = TrainerId;
    this.Name = Name;
    this.Email = Email;
    this.Phone = Phone;
    this.Technology = Technology;
    this.Skills = Skills;
  }
}

export default Trainer;
```

**4. Create `TrainersMock.js` — mock trainer data**
```jsx
import Trainer from './Trainer';

const TrainersMock = [
  new Trainer(1, 'Arun Kumar', 'arun.kumar@example.com', '9876543210', 'Java', ['Spring Boot', 'Microservices', 'SQL']),
  new Trainer(2, 'Divya Shree', 'divya.shree@example.com', '9876543211', 'React', ['React', 'Redux', 'JavaScript']),
  new Trainer(3, 'Karthik Raja', 'karthik.raja@example.com', '9876543212', '.NET', ['C#', 'ASP.NET Core', 'Azure']),
  new Trainer(4, 'Meena Rani', 'meena.rani@example.com', '9876543213', 'Python', ['Django', 'Flask', 'Data Analysis']),
  new Trainer(5, 'Suresh Babu', 'suresh.babu@example.com', '9876543214', 'DevOps', ['Docker', 'Kubernetes', 'Jenkins'])
];

export default TrainersMock;
```

**5. Install React Router for the DOM**
```bash
npm install react-router-dom
```

**6. Create the `TrainersList` component (`TrainersList.js`)**
Accepts the trainer data as a prop and renders each trainer's name as a clickable link:
```jsx
import React from 'react';
import { Link } from 'react-router-dom';

function TrainersList({ trainers }) {
  return (
    <div>
      <h2>Trainers List</h2>
      <ul>
        {trainers.map((trainer) => (
          <li key={trainer.TrainerId}>
            <Link to={`/trainers/${trainer.TrainerId}`}>{trainer.Name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;
```

**7. Create the `Home` component (`Home.js`)**
```jsx
import React from 'react';

function Home() {
  return (
    <div>
      <h1>Welcome to Cognizant Academy — Trainers Directory</h1>
      <p>Use the navigation menu to browse the list of trainers and their expertise.</p>
    </div>
  );
}

export default Home;
```

**8. Modify `App.js` to add routing and navigation links**
```jsx
import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainersList';
import TrainerDetail from './TrainerDetail';
import TrainersMock from './TrainersMock';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link> | <Link to="/trainers">Trainers</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/trainers" element={<TrainersList trainers={TrainersMock} />} />
        <Route path="/trainers/:id" element={<TrainerDetail trainers={TrainersMock} />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```
- `/` renders the `Home` component
- `/trainers` renders the `TrainersList` component
- `/trainers/:id` renders the `TrainerDetail` component for a specific trainer

**9. Create the `TrainerDetail` component (`TrainerDetail.js`)**
Reads the `id` parameter from the URL using `useParams()` and looks up the matching trainer from the mock data:
```jsx
import React from 'react';
import { useParams } from 'react-router-dom';

function TrainerDetail({ trainers }) {
  const { id } = useParams();
  const trainer = trainers.find((t) => t.TrainerId === Number(id));

  if (!trainer) {
    return <h2>Trainer not found</h2>;
  }

  return (
    <div>
      <h2>Trainer Details</h2>
      <p><strong>T-ID:</strong> {trainer.TrainerId}</p>
      <p><strong>Name:</strong> {trainer.Name}</p>
      <p><strong>Email:</strong> {trainer.Email}</p>
      <p><strong>Phone:</strong> {trainer.Phone}</p>
      <p><strong>Stream:</strong> {trainer.Technology}</p>
      <p><strong>Skills:</strong> {trainer.Skills.join(', ')}</p>
    </div>
  );
}

export default TrainerDetail;
```

**10. Build and run the application**
```bash
npm start
```

Open a browser and navigate to:
```
http://localhost:3000
```

- **Home (`/`)** — displays the welcome message and navigation menu
- **Trainers List (`/trainers`)** — displays all trainer names as clickable links
- **Trainer Details (`/trainers/:id`)** — clicking a trainer's name navigates here and shows their full details (T-ID, Name, Email, Phone, Stream, Skills)

---

## Project Structure

```
TrainersApp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Trainer.js
│   ├── TrainersMock.js
│   ├── TrainersList.js
│   ├── TrainerDetail.js
│   ├── Home.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered React Router — its need and benefits, core components (`BrowserRouter`, `Routes`, `Route`, `Link`), the types of router components, and how to pass parameters via the URL using `useParams`. The `TrainersApp` was built with a `Trainer` model, mock trainer data, a `TrainersList` component with clickable navigation, a `Home` component, and a `TrainerDetail` component that reads the trainer ID from the URL to display full details. Routing was configured in `App.js`, and the application ran successfully at `http://localhost:3000` with working navigation between Home, Trainers List, and Trainer Details.
