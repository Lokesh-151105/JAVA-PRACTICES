# React Hands-on Lab — scorecalculatorapp (Function Component & Styling)

## Objectives

- Explain React components
- Identify the differences between components and JavaScript functions
- Identify the types of components
- Explain class component
- Explain function component
- Define component constructor
- Define render() function

## Concepts

### React Components (recap)
A component is an independent, reusable piece of code that returns UI (JSX). Applications are built by composing components together, and each component can accept inputs called **props**.

### Components vs JavaScript Functions

| Aspect | React Component | Plain JavaScript Function |
|---|---|---|
| Purpose | Returns UI (JSX) to be rendered | Performs logic/computation, returns any value |
| Naming | Must start with an uppercase letter | Can start with lowercase or uppercase |
| Return value | JSX / React elements | Any data type (number, string, object, etc.) |
| State & lifecycle | Can have state (class) or hooks (function) | No built-in state or lifecycle |
| Usage | Used like an HTML tag: `<CalculateScore />` | Called like a normal function: `functionName()` |

### Types of Components
1. **Class Components** – ES6 classes extending `React.Component`, requiring a `render()` method.
2. **Function Components** – plain JavaScript functions that accept `props` and return JSX. Simpler to write, and used with Hooks for state/lifecycle behavior.

### Class Component
```jsx
class Example extends React.Component {
  render() {
    return <h2>Hello</h2>;
  }
}
```

### Function Component
```jsx
function Example(props) {
  return <h2>Hello, {props.name}</h2>;
}
```
Function components receive `props` as a parameter and simply return JSX — no `this`, no constructor required.

### Component Constructor
In a **class** component, the `constructor()` runs before mounting and is used to initialize `this.state` and bind event handlers. Function components don't use a constructor; instead, they use the `useState` Hook (if state is needed) or simply work directly with the `props` passed to them.

### render() Function
In a class component, `render()` is required and returns the JSX to display. A function component doesn't have a separate `render()` method — the function body itself acts as the render logic, and the `return` statement produces the JSX.

---

## Hands-on: Student Management Portal — "scorecalculatorapp"

**Goal:** Create a function component named `CalculateScore` that accepts `Name`, `School`, `Total`, and `Goal` as props, calculates the average score, displays the result, and is styled using an external CSS file.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app scorecalculatorapp
```

**2. Create a Components folder**
Inside `scorecalculatorapp/src`, create a folder named `Components`, and inside it create a file named `CalculateScore.js`.

**3. Add code to `CalculateScore.js`**
```jsx
import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const { Name, School, Total, Goal } = props;
  const average = (Total / Goal).toFixed(2);

  return (
    <div className="score-card">
      <h2>Student Score Calculator</h2>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Score:</strong> {Total}</p>
      <p><strong>Goal (No. of Subjects):</strong> {Goal}</p>
      <p><strong>Average Score:</strong> {average}</p>
    </div>
  );
}

export default CalculateScore;
```

**4. Create a Stylesheets folder**
Inside `src`, create a folder named `Stylesheets` and add a file named `mystyle.css`:

```css
.score-card {
  max-width: 400px;
  margin: 40px auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #f0f8ff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  font-family: Arial, sans-serif;
  text-align: left;
}

.score-card h2 {
  color: #2c3e50;
  text-align: center;
  margin-bottom: 15px;
}

.score-card p {
  font-size: 16px;
  margin: 8px 0;
  color: #34495e;
}
```

**5. Edit `App.js` to invoke the `CalculateScore` function component**
```jsx
import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore
        Name="Priya"
        School="Sri Sairam Engineering College"
        Total={450}
        Goal={5}
      />
    </div>
  );
}

export default App;
```

**6. Run the application**
```bash
cd scorecalculatorapp
npm start
```

**7. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

The page displays a styled card showing the student's Name, School, Total, Goal, and the calculated Average Score.

---

## Project Structure

```
scorecalculatorapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Components/
│   │   └── CalculateScore.js
│   ├── Stylesheets/
│   │   └── mystyle.css
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered React function components and how they differ from class components in terms of props handling, constructors, and rendering. A function component, `CalculateScore`, was created to accept `Name`, `School`, `Total`, and `Goal` as props, compute the average score, and display it. The component was styled using an external stylesheet (`mystyle.css`) and rendered successfully from `App.js`, with the app running at `http://localhost:3000`.
