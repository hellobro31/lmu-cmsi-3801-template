import { open } from "node:fs/promises"

export function change(amount) {
  if (!Number.isInteger(amount)) {
    throw new TypeError("Amount must be an integer")
  }
  if (amount < 0) {
    throw new RangeError("Amount cannot be negative")
  }
  let [counts, remaining] = [{}, amount]
  for (const denomination of [25, 10, 5, 1]) {
    counts[denomination] = Math.floor(remaining / denomination)
    remaining %= denomination
  }
  return counts
}

// Write your first then lower case function here
function firstThenLowerCase(sequence, predicate) {
  return sequence.find(predicate)?.toLowerCase();
}

// Write your powers generator here
function* powersGenerator({ ofBase, upTo }) {
  let power = 1;
  while (power <= upTo) {
      yield power;
      power *= ofBase;
  }
}

// Write your say function here

function say(word = "") {
  const words = []; // Array to store the words

  function inner(nextWord) {
    if (nextWord === undefined) {
      return words.join(" "); // Return joined words if no argument is passed
    }
    words.push(nextWord);
    return inner; 
  }

  words.push(word); 

  return inner;

}
// Write your line count function here
const fs = require('fs').promises;

async function meaningfulLineCount(filename) {
    try {
        const data = await fs.readFile(filename, 'utf-8');
        const lines = data.split('\n');

        const count = lines.reduce((acc, line) => {
            const trimmedLine = line.trim();
            if (trimmedLine !== '' && !trimmedLine.startsWith('#')) {
                return acc + 1;
            }
            return acc;
        }, 0);

        return count;
    } catch (error) {
        throw new Error('No such file');  // Propagate the error as a rejection
    }
}
// Write your Quaternion class here
class Quaternion {
  constructor(a, b, c, d) {
    this._a = a;
    this._b = b;
    this._c = c;
    this._d = d;
    Object.freeze(this); // Make the object immutable
  }

  get coefficients() {
    return [this._a, this._b, this._c, this._d];
  }

  // Getter for the conjugate
  get conjugate() {
    return new Quaternion(this._a, -this._b, -this._c, -this._d);
  }

  // Method to add two quaternions
  plus(q) {
    return new Quaternion(this._a + q._a, this._b + q._b, this._c + q._c, this._d + q._d);
  }

  // Method to multiply two quaternions
  times(q) {
    const a = this._a * q._a - this._b * q._b - this._c * q._c - this._d * q._d;
    const b = this._a * q._b + this._b * q._a + this._c * q._d - this._d * q._c;
    const c = this._a * q._c - this._b * q._d + this._c * q._a + this._d * q._b;
    const d = this._a * q._d + this._b * q._c - this._c * q._b + this._d * q._a;
    return new Quaternion(a, b, c, d);
  }

  equals(q) {
    return this._a === q._a && this._b === q._b && this._c === q._c && this._d === q._d;
  }

  toString() {
    let terms = [];

    if (this._a !== 0) terms.push(`${this._a}`);
    if (this._b !== 0) terms.push(`${this._b === 1 ? '' : this._b === -1 ? '-' : this._b}i`);
    if (this._c !== 0) terms.push(`${this._c === 1 ? '' : this._c === -1 ? '-' : this._c}j`);
    if (this._d !== 0) terms.push(`${this._d === 1 ? '' : this._d === -1 ? '-' : this._d}k`);

    return terms.join('+').replace(/\+\-/g, '-'); // Join terms and correct formatting of signs
  }
}
