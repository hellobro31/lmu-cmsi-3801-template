from dataclasses import dataclass
from collections.abc import Callable


def change(amount: int) -> dict[int, int]:
    if not isinstance(amount, int):
        raise TypeError('Amount must be an integer')
    if amount < 0:
        raise ValueError('Amount cannot be negative')
    counts, remaining = {}, amount
    for denomination in (25, 10, 5, 1):
        counts[denomination], remaining = divmod(remaining, denomination)
    return counts


# Write your first then lower case function here
def first_then_lower_case(sequence, predicate):
    for element in sequence:
        if predicate(element):
            return element.lower()
    return None


# Write your powers generator here
def powers_generator(*, base, limit):
    power = 1
    while power <= limit:
        yield power
        power *= base

def say(word=""):
    words = [] 

    def inner(next_word=None):
        if next_word is None:
            return " ".join(words)
        words.append(next_word)
        return inner  

    words.append(word)

    return inner







def meaningful_line_count(filename):
    count = 0
    try:
        with open(filename, 'r') as file:  # Auto-closes the file
            for line in file:
                stripped_line = line.strip()  # Strip whitespace
                if stripped_line and not stripped_line.startswith('#'):
                    count += 1
    except FileNotFoundError:
        raise FileNotFoundError("No such file") 
    return count

# Write your Quaternion class here


from dataclasses import dataclass

@dataclass(frozen=True)
class Quaternion:
    a: float
    b: float
    c: float
    d: float

    @property
    def coefficients(self):
        return (self.a, self.b, self.c, self.d)

    @property
    def conjugate(self):
        return Quaternion(self.a, -self.b, -self.c, -self.d)

    def __add__(self, other):
        if not isinstance(other, Quaternion):
            return NotImplemented
        return Quaternion(self.a + other.a, self.b + other.b, self.c + other.c, self.d + other.d)

    def __mul__(self, other):
        if not isinstance(other, Quaternion):
            return NotImplemented
        a = self.a * other.a - self.b * other.b - self.c * other.c - self.d * other.d
        b = self.a * other.b + self.b * other.a + self.c * other.d - self.d * other.c
        c = self.a * other.c - self.b * other.d + self.c * other.a + self.d * other.b
        d = self.a * other.d + self.b * other.c - self.c * other.b + self.d * other.a
        return Quaternion(a, b, c, d)

    def __eq__(self, other):
        if not isinstance(other, Quaternion):
            return False
        return (self.a, self.b, self.c, self.d) == (other.a, other.b, other.c, other.d)

    def __str__(self):
        terms = []
        if self.a != 0:
            terms.append(f"{self.a}")

        # Handle 'i' term
        if self.b != 0:
            b_str = f"{self.b}" if abs(self.b) != 1 else ("-" if self.b == -1 else "")
            terms.append(f"{b_str}i")

        # Handle 'j' term
        if self.c != 0:
            c_str = f"{self.c}" if abs(self.c) != 1 else ("-" if self.c == -1 else "")
            terms.append(f"{c_str}j")

        # Handle 'k' term
        if self.d != 0:
            d_str = f"{self.d}" if abs(self.d) != 1 else ("-" if self.d == -1 else "")
            terms.append(f"{d_str}k")

        # Handle the case where all terms are zero
        if not terms:
            return "0"

        # Join the terms, ensuring the correct placement of '+' and '-' signs
        result = ''.join([terms[0]] + [f"+{term}" if not term.startswith('-') else term for term in terms[1:]])

        return result
