# React Hands-on Lab — officespacerentalapp (JSX)

## Objectives

- Define JSX
- Explain about ECMAScript
- Explain `React.createElement()`
- Explain how to create React nodes with JSX
- Define how to render JSX to DOM
- Explain how to use JavaScript expressions in JSX
- Explain how to use inline CSS in JSX

## Concepts

### What is JSX?
JSX (JavaScript XML) is a syntax extension for JavaScript that allows writing HTML-like markup directly within JavaScript code. It is not valid JavaScript by itself — it is compiled (transpiled) by tools like Babel into regular JavaScript function calls (`React.createElement()`), which React then uses to build the UI.

```jsx
const heading = <h1>Office Space</h1>;
```

### About ECMAScript
ECMAScript (ES) is the standardized specification that JavaScript is based on. Each version (ES5, ES6/ES2015, ES2016, and onward) introduces new language features. React and JSX rely heavily on modern ECMAScript features such as arrow functions, template literals, destructuring, classes, and modules, which are compiled down to older JavaScript for browser compatibility.

### `React.createElement()`
Under the hood, every piece of JSX is converted into a call to `React.createElement()`, which takes the element type, its props/attributes, and its children, and returns a plain JavaScript object called a React element.

```jsx
// JSX
const element = <h1 className="title">Office Space</h1>;

// Compiles to:
const element = React.createElement(
  'h1',
  { className: 'title' },
  'Office Space'
);
```

### Creating React Nodes with JSX
JSX allows nesting elements just like HTML, and any JSX expression evaluates to a React element (a node) that can be rendered, stored in a variable, passed as props, or returned from a component.

```jsx
const card = (
  <div>
    <h1>Office Space</h1>
    <p>Chennai</p>
  </div>
);
```

### Rendering JSX to the DOM
To display a React element on the actual web page, it must be rendered into a real DOM node using `ReactDOM.render()` (or `ReactDOM.createRoot().render()` in React 18+), targeting a container element such as `<div id="root"></div>` in `index.html`.

```jsx
import ReactDOM from 'react-dom';

ReactDOM.render(<App />, document.getElementById('root'));
```

### JavaScript Expressions in JSX
Any valid JavaScript expression can be embedded inside JSX using curly braces `{ }`. This includes variables, function calls, arithmetic, ternary conditions, and object property access.

```jsx
const name = 'DBS';
const element = <h1>Name: {name}</h1>;

// Property access
const office = { Name: 'DBS', Rent: 50000 };
<h1>Name: {office.Name}</h1>
```

### Inline CSS in JSX
Inline styles in JSX are applied via the `style` attribute, which accepts a JavaScript object with camelCase CSS property names, or via conditionally applied CSS class names using `className`.

```jsx
<h3 style={{ color: 'red' }}>Rent: Rs. 50000</h3>

// or via a conditional class name
<h3 className={rent <= 60000 ? 'textRed' : 'textGreen'}>Rent: Rs. {rent}</h3>
```

---

## Hands-on: Office Space Rental — "officespacerentalapp"

**Goal:** Build a React app using JSX to create elements, attributes, and render the DOM, displaying office space details with conditional inline styling based on rent.

### Requirements
- Create an element to display the heading of the page.
- Use an attribute to display the image of the office space.
- Create an object of `office` to display details like Name, Rent, and Address.
- Create a list of objects and loop through the office space items to display more data.
- Apply CSS to display the Rent in **Red** if it's 60000 or below, and in **Green** if it's above 60000.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app officespacerentalapp
```

**2. Open the project in VS Code**
```bash
cd officespacerentalapp
code .
```

**3. Add a stylesheet for the conditional text colors**
Create `App.css` (or add to the existing one):
```css
.textRed {
  color: red;
}

.textGreen {
  color: green;
}
```

**4. Build `App.js` using JSX**

Create the heading element, the image using an attribute, an office object, and apply conditional styling to the Rent based on its value:

```jsx
import React from 'react';
import './App.css';
import officeImage from './office.jpg'; // sample office space image

function App() {
  // Element for the page heading
  const element = 'Office Space';

  // Attribute usage - image element
  const jsxatt = (
    <img src={officeImage} width="25%" height="25%" alt="Office Space" />
  );

  // Object holding office space details
  const ItemName = { Name: 'DBS', Rent: 50000, Address: 'Chennai' };

  // Determine text color class based on Rent
  let colors = [];
  if (ItemName.Rent <= 60000) {
    colors.push('textRed');
  } else {
    colors.push('textGreen');
  }

  return (
    <div>
      <h1>{element} , at Affordable Range </h1>
      {jsxatt}
      <h1>Name: {ItemName.Name}</h1>
      <h3 className={colors[0]}> Rent: Rs. {ItemName.Rent}</h3>
      <h3> Address: {ItemName.Address}</h3>
    </div>
  );
}

export default App;
```

**5. Extend it to a list of office spaces (looping through objects)**

To display more data, create an array of office objects and map through them, applying the same rent-based coloring logic to each entry:

```jsx
import React from 'react';
import './App.css';

const officeList = [
  { Name: 'DBS', Rent: 50000, Address: 'Chennai' },
  { Name: 'Prestige Towers', Rent: 75000, Address: 'Bengaluru' },
  { Name: 'RMZ Millenia', Rent: 58000, Address: 'Chennai' },
  { Name: 'One Horizon Centre', Rent: 90000, Address: 'Gurugram' }
];

function App() {
  const element = 'Office Space';

  return (
    <div>
      <h1>{element} , at Affordable Range </h1>
      {officeList.map((item, index) => {
        const colorClass = item.Rent <= 60000 ? 'textRed' : 'textGreen';
        return (
          <div key={index}>
            <h1>Name: {item.Name}</h1>
            <h3 className={colorClass}> Rent: Rs. {item.Rent}</h3>
            <h3> Address: {item.Address}</h3>
            <hr />
          </div>
        );
      })}
    </div>
  );
}

export default App;
```

**6. Verify `index.js` renders JSX to the DOM**
```jsx
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(<App />, document.getElementById('root'));
```

**7. Run the application**
```bash
npm start
```

**8. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

The page displays:
```
Office Space , at Affordable Range
[office space image]
Name: DBS
Rent: Rs. 50000   (shown in red, since 50000 ≤ 60000)
Address: Chennai
```

When the list version is used, every office entry is displayed with its Rent colored **red** if it's 60000 or below, and **green** if it's above 60000.

---

## Project Structure

```
officespacerentalapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── App.js
│   ├── App.css
│   ├── office.jpg
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered JSX — how it compiles down to `React.createElement()` calls, how React nodes are created and rendered to the DOM, how to embed JavaScript expressions inside JSX, and how to apply inline/conditional CSS. The `officespacerentalapp` was built to display an office space heading, an image via an attribute, and office details (Name, Rent, Address) from a JavaScript object, with the Rent value conditionally styled red or green based on its amount, and the application ran successfully at `http://localhost:3000`.
