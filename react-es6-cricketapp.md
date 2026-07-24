# React Hands-on Lab — cricketapp (ES6 Features in React)

## Objectives

- List the features of ES6
- Explain JavaScript `let`
- Identify the differences between `var` and `let`
- Explain JavaScript `const`
- Explain ES6 class fundamentals
- Explain ES6 class inheritance
- Define ES6 arrow functions
- Identify `set()`, `map()`

## Concepts

### Features of ES6
ECMAScript 6 (ES6 / ES2015) introduced several modern JavaScript features widely used in React development:
- `let` and `const` for block-scoped variable declarations
- Arrow functions (`=>`) for concise function syntax
- Classes with constructors and inheritance
- Template literals for string interpolation
- Destructuring of arrays and objects
- Spread/rest operators (`...`)
- Default parameters
- `Map` and `Set` data structures
- Modules (`import` / `export`)
- Promises for asynchronous programming

### JavaScript `let`
`let` declares a **block-scoped** variable — it is only accessible within the block (`{ }`) where it's defined. Unlike `var`, a `let` variable cannot be re-declared in the same scope and is not hoisted in a usable state (it exists in a "temporal dead zone" until initialized).

```javascript
let score = 70;
if (true) {
  let score = 90; // different variable, scoped to this block
  console.log(score); // 90
}
console.log(score); // 70
```

### Differences Between `var` and `let`

| Aspect | `var` | `let` |
|---|---|---|
| Scope | Function-scoped | Block-scoped |
| Re-declaration | Allowed in the same scope | Not allowed in the same scope |
| Hoisting | Hoisted and initialized as `undefined` | Hoisted but not initialized (temporal dead zone) |
| Global object property | Becomes a property of `window` (in browsers) | Does not become a property of `window` |

### JavaScript `const`
`const` declares a **block-scoped**, read-only reference to a value. The variable itself cannot be reassigned after declaration, though objects/arrays declared with `const` can still have their contents mutated.

```javascript
const players = ['Sachin', 'Virat', 'Dhoni'];
players.push('Rohit'); // allowed — mutating contents
players = [];          // error — cannot reassign
```

### ES6 Class Fundamentals
ES6 introduced `class` syntax as syntactic sugar over JavaScript's prototype-based inheritance, making object-oriented code more readable.

```javascript
class Player {
  constructor(name, score) {
    this.name = name;
    this.score = score;
  }

  displayInfo() {
    return `${this.name}: ${this.score}`;
  }
}
```

### ES6 Class Inheritance
A class can extend another class using the `extends` keyword, inheriting its properties and methods. The `super()` call invokes the parent class's constructor.

```javascript
class Batsman extends Player {
  constructor(name, score, strikeRate) {
    super(name, score);
    this.strikeRate = strikeRate;
  }
}
```

### ES6 Arrow Functions
Arrow functions provide a shorter syntax for writing functions and do not have their own `this` binding (they inherit `this` from the enclosing scope).

```javascript
const add = (a, b) => a + b;

players.map((item) => {
  return item.name;
});
```

### `set()` and `map()`
- **`Map`** — an ES6 data structure that stores key-value pairs, preserving insertion order and allowing keys of any type.
  ```javascript
  const scores = new Map();
  scores.set('Sachin', 50);
  scores.set('Virat', 70);
  ```
- **`Array.prototype.map()`** — an array method that creates a **new array** by applying a function to every element of the original array. It is heavily used in React to render lists of components from an array of data.
  ```javascript
  const doubled = [1, 2, 3].map((n) => n * 2); // [2, 4, 6]
  ```

---

## Hands-on: Cricket Application — "cricketapp"

**Goal:** Build a React app using ES6 features — `map()`, arrow functions, destructuring, and the spread operator — to display cricket player data.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app cricketapp
```

**2. Open the project in VS Code**
```bash
cd cricketapp
code .
```

### Component 1: ListofPlayers

**3. Declare an array of 11 players (`players.js`)**
```javascript
const players = [
  { name: 'Sachin', score: 50 },
  { name: 'Michael', score: 70 },
  { name: 'John', score: 40 },
  { name: 'Jon', score: 61 },
  { name: 'Elisabeth', score: 61 },
  { name: 'Dhoni', score: 95 },
  { name: 'Rohit', score: 100 },
  { name: 'Virat', score: 84 },
  { name: 'Jadeja', score: 64 },
  { name: 'Raina', score: 75 },
  { name: 'Rohit', score: 80 }
];

export default players;
```

**4. Create the `ListofPlayers` component — display names and scores using `map()`**
```jsx
import React from 'react';

function ListofPlayers({ players }) {
  return (
    <div>
      {players.map((item) => {
        return (
          <div>
            <li>Mr. {item.name} <span>{item.score}</span></li>
          </div>
        );
      })}
    </div>
  );
}

export default ListofPlayers;
```

**5. Filter players with scores below 70 using an arrow function (`Scorebelow70` component)**
```jsx
import React from 'react';

function Scorebelow70({ players }) {
  const players70 = [];
  players.map((item) => {
    if (item.score <= 70) {
      players70.push(item);
    }
  });

  return (
    <div>
      {players70.map((item) => (
        <li>Mr. {item.name} <span>{item.score}</span></li>
      ))}
    </div>
  );
}

export default Scorebelow70;
```

### Component 2: IndianPlayers

**6a. Display Odd Team and Even Team players using ES6 Destructuring**
```jsx
export function OddPlayers([first, , third, , fifth]) {
  return (
    <div>
      <li> First : {first} </li>
      <li> Third : {third} </li>
      <li> Fifth : {fifth}</li>
    </div>
  );
}

export function EvenPlayers([, second, , fourth, , sixth]) {
  return (
    <div>
      <li> Second : {second} </li>
      <li> Fourth : {fourth} </li>
      <li> Sixth : {sixth}</li>
    </div>
  );
}
```
The commas in the destructuring pattern (`[first, , third, , fifth]`) skip over array elements, picking out only the 1st, 3rd, and 5th items (Odd positions) or 2nd, 4th, and 6th items (Even positions).

**6b. Declare and merge two arrays using the ES6 spread operator**
```javascript
const T20Players = ['First Player', 'Second Player', 'Third Player'];
const RanjiTrophyPlayers = ['Fourth Player', 'Fifth Player', 'Sixth Player'];
export const IndianPlayers = [...T20Players, ...RanjiTrophyPlayers];
```

**Create the `ListofIndianPlayers` component to display the merged list**
```jsx
import React from 'react';

function ListofIndianPlayers({ IndianPlayers }) {
  return (
    <div>
      {IndianPlayers.map((item) => (
        <li>Mr. {item}</li>
      ))}
    </div>
  );
}

export default ListofIndianPlayers;
```

**7. Sample `IndianTeam` data for Odd/Even destructuring**
```javascript
export const IndianTeam = ['Sachin1', 'Dhoni2', 'Virat3', 'Rohit4', 'Yuvraj5', 'Raina6'];
```

### Combine Both Components Using a Flag

**8. Edit `App.js` to toggle between the two sets of components using a `flag` variable**
```jsx
import React from 'react';
import players from './players';
import ListofPlayers from './ListofPlayers';
import Scorebelow70 from './Scorebelow70';
import { OddPlayers, EvenPlayers } from './IndianPlayers';
import ListofIndianPlayers from './ListofIndianPlayers';
import { IndianPlayers } from './IndianPlayersData';
import { IndianTeam } from './IndianTeam';

function App() {
  var flag = true;

  if (flag === true) {
    return (
      <div>
        <h1> List of Players</h1>
        <ListofPlayers players={players} />
        <hr />
        <h1> List of Players having Scores Less than 70 </h1>
        <Scorebelow70 players={players} />
      </div>
    );
  } else {
    return (
      <div>
        <div>
          <h1> Indian Team </h1>
          <h1> Odd Players </h1>
          {OddPlayers(IndianTeam)}
          <hr />
          <h1> Even Players</h1>
          {EvenPlayers(IndianTeam)}
        </div>
        <hr />
        <div>
          <h1> List of Indian Players Merged</h1>
          <ListofIndianPlayers IndianPlayers={IndianPlayers} />
        </div>
      </div>
    );
  }
}

export default App;
```

**9. Run the application**
```bash
npm start
```

**10. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

**When `flag = true`:**
- **List of Players** — all 11 players with their names and scores
- **List of Players having Scores Less than 70** — filtered list (e.g., Jack 50, Michael 70, John 40, Jon 61, Elisabeth 61, Jadeja 64)

**When `flag = false`:**
- **Odd Players** — First: Sachin1, Third: Virat3, Fifth: Yuvraj5
- **Even Players** — Second: Dhoni2, Fourth: Rohit4, Sixth: Raina6
- **List of Indian Players Merged** — Mr. First Player, Mr. Second Player, Mr. Third Player, Mr. Fourth Player, Mr. Fifth Player, Mr. Sixth Player

---

## Project Structure

```
cricketapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── players.js
│   ├── ListofPlayers.js
│   ├── Scorebelow70.js
│   ├── IndianPlayers.js
│   ├── IndianPlayersData.js
│   ├── ListofIndianPlayers.js
│   ├── IndianTeam.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered core ES6 features used in React — `let` vs `var`, `const`, class fundamentals and inheritance, arrow functions, and `Map`/`Set`. The `cricketapp` application was built using `Array.prototype.map()` to render a list of 11 players, arrow functions to filter players with scores below 70, ES6 array destructuring to extract Odd and Even team players, and the spread operator to merge two player arrays. Both sets of components were toggled on the same home page using a simple `flag` variable, with the application running successfully at `http://localhost:3000`.
