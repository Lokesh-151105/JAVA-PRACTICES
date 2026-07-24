# React Hands-on Lab — ticketbookingapp (Conditional Rendering)

## Objectives

- Explain about conditional rendering in React
- Define element variables
- Explain how to prevent components from rendering

## Concepts

### Conditional Rendering in React
Conditional rendering means displaying different UI depending on the application's state or props — much like conditional logic (`if`/`else`) in regular JavaScript, but applied to what JSX gets returned. React doesn't have special syntax for this; it uses standard JavaScript conditionals (`if`, ternary `? :`, logical `&&`) to decide which elements to render.

```jsx
function Greeting(props) {
  if (props.isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
}
```

### Element Variables
A React element can be stored in a variable, which allows part of a component's output to be conditionally assigned and then rendered later in the JSX. This is useful when a component needs to decide between rendering one of several possible elements.

```jsx
function LoginControl(props) {
  const isLoggedIn = props.isLoggedIn;
  let button;

  if (isLoggedIn) {
    button = <LogoutButton onClick={props.handleLogoutClick} />;
  } else {
    button = <LoginButton onClick={props.handleLoginClick} />;
  }

  return (
    <div>
      <Greeting isLoggedIn={isLoggedIn} />
      {button}
    </div>
  );
}
```
Here, `button` is an **element variable** — it holds a JSX element chosen conditionally, and is later rendered using `{button}`.

### Preventing a Component from Rendering
Sometimes a component should render nothing at all based on a prop or condition. This is done by returning `null` from the component's `render()` method (class component) or function body (function component), instead of returning JSX. Returning `null` does not affect the component's lifecycle — it simply results in no output for that render.

```jsx
function WarningBanner(props) {
  if (!props.warn) {
    return null; // renders nothing
  }
  return <div className="warning">Warning!</div>;
}
```

---

## Hands-on: Flight Ticket Booking — "ticketbookingapp"

**Scenario:** A guest user can browse the page and view flight details, but only a logged-in user can book tickets. The Login and Logout buttons should conditionally display different pages — logging in shows the User page, and clicking Logout shows the Guest page again.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app ticketbookingapp
```

**2. Open the project in VS Code**
```bash
cd ticketbookingapp
code .
```

**3. Create the `LoginButton` component**
```jsx
function LoginButton(props) {
  return (
    <button onClick={props.onClick}>
      Login
    </button>
  );
}

export default LoginButton;
```

**4. Create the `LogoutButton` component**
```jsx
function LogoutButton(props) {
  return (
    <button onClick={props.onClick}>
      Logout
    </button>
  );
}

export default LogoutButton;
```

**5. Create the `GuestGreeting` and `UserGreeting` components**
```jsx
function GuestGreeting() {
  return <h1>Please sign up.</h1>;
}

export default GuestGreeting;
```

```jsx
function UserGreeting() {
  return <h1>Welcome back</h1>;
}

export default UserGreeting;
```

**6. Create the `Greeting` component that conditionally renders based on login status**
```jsx
import UserGreeting from './UserGreeting';
import GuestGreeting from './GuestGreeting';

function Greeting(props) {
  const isLoggedIn = props.isLoggedIn;
  if (isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
}

export default Greeting;
```

**7. Create the flight details component (only visible to logged-in users)**
This is where the conditional-rendering / "prevent from rendering" concept applies — flight *booking* options render only when the user is logged in; guests can browse the page but not book.

```jsx
function FlightDetails(props) {
  if (!props.isLoggedIn) {
    return null; // Booking option is not rendered for guests
  }
  return (
    <div>
      <h3>Available Flights</h3>
      <p>Chennai → Bengaluru — 6:00 AM — ₹3,499</p>
      <button>Book Ticket</button>
    </div>
  );
}

export default FlightDetails;
```

**8. Build the main `LoginControl` component using an element variable to toggle Login/Logout**
```jsx
import React, { Component } from 'react';
import LoginButton from './LoginButton';
import LogoutButton from './LogoutButton';
import Greeting from './Greeting';
import FlightDetails from './FlightDetails';

class LoginControl extends Component {
  constructor(props) {
    super(props);
    this.state = { isLoggedIn: false };
    this.handleLoginClick = this.handleLoginClick.bind(this);
    this.handleLogoutClick = this.handleLogoutClick.bind(this);
  }

  handleLoginClick() {
    this.setState({ isLoggedIn: true });
  }

  handleLogoutClick() {
    this.setState({ isLoggedIn: false });
  }

  render() {
    const isLoggedIn = this.state.isLoggedIn;
    let button;

    if (isLoggedIn) {
      button = <LogoutButton onClick={this.handleLogoutClick} />;
    } else {
      button = <LoginButton onClick={this.handleLoginClick} />;
    }

    return (
      <div>
        <Greeting isLoggedIn={isLoggedIn} />
        {button}
        <FlightDetails isLoggedIn={isLoggedIn} />
      </div>
    );
  }
}

export default LoginControl;
```

**9. Update `App.js` to render `LoginControl`**
```jsx
import React from 'react';
import LoginControl from './LoginControl';

function App() {
  return (
    <div className="App">
      <LoginControl />
    </div>
  );
}

export default App;
```

**10. Run the application**
```bash
npm start
```

**11. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

**When not logged in (Guest):**
```
Please sign up.
[Login]
```
Flight booking options are not rendered (the `FlightDetails` component returns `null`).

**After clicking "Login":**
```
Welcome back
[Logout]

Available Flights
Chennai → Bengaluru — 6:00 AM — ₹3,499
[Book Ticket]
```

Clicking **Logout** switches the state back to the Guest view, hiding the flight booking section again.

---

## Project Structure

```
ticketbookingapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── LoginButton.js
│   ├── LogoutButton.js
│   ├── GuestGreeting.js
│   ├── UserGreeting.js
│   ├── Greeting.js
│   ├── FlightDetails.js
│   ├── LoginControl.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered conditional rendering in React — using standard JavaScript conditionals to decide what to render, storing conditionally chosen elements in element variables, and preventing a component from rendering by returning `null`. The `ticketbookingapp` was built with `LoginButton`/`LogoutButton`, `Greeting` (switching between `GuestGreeting` and `UserGreeting`), and a `FlightDetails` component that only renders booking options for logged-in users, all coordinated through a `LoginControl` component's state, with the application running successfully at `http://localhost:3000`.
