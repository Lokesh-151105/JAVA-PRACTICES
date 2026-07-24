# React Hands-on Lab — eventexamplesapp (React Events)

## Objectives

- Explain React events
- Explain about event handlers
- Define Synthetic event
- Identify React event naming convention

## Concepts

### React Events
React events are actions triggered by user interaction with UI elements — clicks, form submissions, key presses, mouse movements, and so on. React handles events in a way very similar to handling DOM events, but with some syntactic differences that make it consistent across browsers.

### Event Handlers
An **event handler** is a function that runs in response to a specific event. In React, event handlers are passed as props to JSX elements and are typically defined as class methods (in class components) or as functions inside the component body (in function components).

```jsx
class Counter extends React.Component {
  handleIncrement = () => {
    console.log('Incremented!');
  };

  render() {
    return <button onClick={this.handleIncrement}>Increment</button>;
  }
}
```

Because class methods are not bound to `this` by default, handlers are commonly defined as arrow functions (class fields) or explicitly bound in the constructor using `this.handleIncrement = this.handleIncrement.bind(this)`, so that `this` correctly refers to the component instance inside the handler.

### Synthetic Event
React wraps the browser's native event object in a cross-browser wrapper called a **SyntheticEvent**. This gives events a consistent interface (properties and methods like `preventDefault()`, `stopPropagation()`, `target`, etc.) regardless of the browser being used, so developers don't need to write browser-specific event-handling code.

```jsx
function handleClick(event) {
  event.preventDefault();
  console.log('Synthetic event type:', event.type);
}
```

### React Event Naming Convention
React events are named using **camelCase** rather than lowercase, and are passed as JSX attributes with a function reference (not a string, unlike plain HTML):

| HTML | React |
|---|---|
| `onclick="handleClick()"` | `onClick={handleClick}` |
| `onchange="handleChange()"` | `onChange={handleChange}` |
| `onsubmit="handleSubmit()"` | `onSubmit={handleSubmit}` |
| `onmouseover="handleHover()"` | `onMouseOver={handleHover}` |

---

## Hands-on: Event Handling — "eventexamplesapp"

**Goal:** Build a React app to demonstrate event handling — counters, functions invoked with arguments, synthetic events, and a small currency converter using a form submit event.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app eventexamplesapp
```

**2. Open the project in VS Code**
```bash
cd eventexamplesapp
code .
```

### Requirement 1: Increment / Decrement Counter (button invoking multiple methods)

**3. Create the counter with Increment and Decrement buttons.**
The **Increment** button invokes two methods when clicked: one to increase the counter value, and another to display a static "Hello" message.

```jsx
import React, { Component } from 'react';

class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }

  incrementValue = () => {
    this.setState({ count: this.state.count + 1 });
  };

  sayHello = () => {
    alert('Hello! Counter was incremented.');
  };

  handleIncrementClick = () => {
    this.incrementValue();
    this.sayHello();
  };

  decrementValue = () => {
    this.setState({ count: this.state.count - 1 });
  };

  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <button onClick={this.handleIncrementClick}>Increment</button>
        <button onClick={this.decrementValue}>Decrement</button>
      </div>
    );
  }
}

export default Counter;
```

### Requirement 2: Say Welcome (function invoked with an argument)

**4. Create a "Say Welcome" button that invokes a function, passing `"welcome"` as an argument.**

```jsx
sayWelcome = (message) => {
  alert(message);
};

render() {
  return (
    <button onClick={() => this.sayWelcome('welcome')}>Say Welcome</button>
  );
}
```
An arrow function is used inline in `onClick` so the argument can be passed at the time the button is clicked, rather than immediately when the component renders.

### Requirement 3: Synthetic Event (onPress-style click)

**5. Create a button that uses the synthetic event object to display a message.**

```jsx
handleClick = (event) => {
  alert('I was clicked');
  console.log('Synthetic event target:', event.target);
};

render() {
  return <button onClick={this.handleClick}>Click on me</button>;
}
```

**Full `Counter.js` combining all three requirements:**
```jsx
import React, { Component } from 'react';

class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }

  incrementValue = () => {
    this.setState({ count: this.state.count + 1 });
  };

  sayHello = () => {
    alert('Hello! Counter was incremented.');
  };

  handleIncrementClick = () => {
    this.incrementValue();
    this.sayHello();
  };

  decrementValue = () => {
    this.setState({ count: this.state.count - 1 });
  };

  sayWelcome = (message) => {
    alert(message);
  };

  handleClick = (event) => {
    alert('I was clicked');
    console.log('Synthetic event target:', event.target);
  };

  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <button onClick={this.handleIncrementClick}>Increment</button>
        <button onClick={this.decrementValue}>Decrement</button>
        <button onClick={() => this.sayWelcome('welcome')}>Say welcome</button>
        <button onClick={this.handleClick}>Click on me</button>
      </div>
    );
  }
}

export default Counter;
```

### Requirement 4: CurrencyConvertor Component (form submit event)

**6. Create a `CurrencyConvertor` component that converts Indian Rupees to Euro when the Convert/Submit button is clicked.**

Handle the click event of the button to invoke a `handleSubmit` method, which reads the amount entered and converts it to Euro.

```jsx
import React, { Component } from 'react';

class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = {
      amount: '',
      currency: 'Euro'
    };
  }

  handleAmountChange = (event) => {
    this.setState({ amount: event.target.value });
  };

  handleCurrencyChange = (event) => {
    this.setState({ currency: event.target.value });
  };

  handleSubmit = () => {
    const inrToEurRate = 0.011; // sample conversion rate
    const converted = (this.state.amount * inrToEurRate).toFixed(3);
    alert(`Converting to ${this.state.currency} amount ${converted}`);
  };

  render() {
    return (
      <div>
        <h2>Currency Convertor!!!</h2>
        <label>
          Amount:
          <input
            type="text"
            value={this.state.amount}
            onChange={this.handleAmountChange}
          />
        </label>
        <br />
        <label>
          Currency:
          <input
            type="text"
            value={this.state.currency}
            onChange={this.handleCurrencyChange}
          />
        </label>
        <br />
        <button onClick={this.handleSubmit}>Submit</button>
      </div>
    );
  }
}

export default CurrencyConvertor;
```

**7. Update `App.js` to render both components**
```jsx
import React from 'react';
import Counter from './Counter';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
  return (
    <div className="App">
      <Counter />
      <hr />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
```

**8. Run the application**
```bash
npm start
```

**9. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

- Clicking **Increment** increases the counter and shows a "Hello" alert (two methods invoked from one click).
- Clicking **Decrement** decreases the counter.
- Clicking **Say welcome** shows an alert with the message `"welcome"`, passed as an argument.
- Clicking **Click on me** shows an alert `"I was clicked"`, using the synthetic event object.
- In the **Currency Convertor** section, entering an amount (e.g., `80`) and a currency (e.g., `Euro`), then clicking **Submit**, triggers `handleSubmit`, which displays an alert such as `Converting to Euro amount 0.880`.

---

## Project Structure

```
eventexamplesapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Counter.js
│   ├── CurrencyConvertor.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered React events — how event handlers are defined and bound to `this`, how the SyntheticEvent wrapper provides a consistent cross-browser event interface, and React's camelCase event naming convention. The `eventexamplesapp` application was built with a `Counter` component demonstrating an Increment button invoking multiple methods, a "Say welcome" button passing an argument, a synthetic click event, and a `CurrencyConvertor` component that handles a submit/click event to convert Indian Rupees to Euro, with the application running successfully at `http://localhost:3000`.
