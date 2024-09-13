function change(amount)
  if math.type(amount) ~= "integer" then
    error("Amount must be an integer")
  end
  if amount < 0 then
    error("Amount cannot be negative")
  end
  local counts, remaining = {}, amount
  for _, denomination in ipairs({25, 10, 5, 1}) do
    counts[denomination] = remaining // denomination
    remaining = remaining % denomination
  end
  return counts
end

-- Write your first then lower case function here
function findLowercasedElement(sequence, predicate)
  for _, element in ipairs(sequence) do
      if predicate(element) then
          return element:lower()
      end
  end
  return nil
end

-- Write your powers generator here
function powers_generator(base, limit)
  local power = 1
  while power <= limit do
      coroutine.yield(power)
      power = power * base
  end
end

g = coroutine.create(function() powers_generator(2, 7) end)
-- Write your say function here
function say(word)
  local words = {}  -- Table to store the words

  local function inner(next_word)
      if next_word == nil then  -- If called without arguments
          return table.concat(words, " ")  -- Concatenate and return the words
      end
      table.insert(words, next_word)  -- Add the word to the list
      return inner  -- Return the inner function for chaining
  end

  if word then  -- If the initial word is provided, add it
      table.insert(words, word)
  end

  return inner
end

-- Write your line count function here
function meaningful_line_count(filename)
  local file, err = io.open(filename, "r")
  if not file then
      error("No such file", 2)
  end

  local count = 0
  for line in file:lines() do
      local trimmed_line = line:match("^%s*(.-)%s*$")  
      if trimmed_line ~= "" and trimmed_line:sub(1, 1) ~= "#" then
          count = count + 1
      end
  end

  file:close()  
  return count
end
-- Write your Quaternion table here
Quaternion = {}
Quaternion.__index = Quaternion

function Quaternion.new(a, b, c, d)
    local self = setmetatable({}, Quaternion)
    self.a = a
    self.b = b
    self.c = c
    self.d = d
    return self
end

-- Overloading the addition operator
function Quaternion.__add(q1, q2)
    return Quaternion.new(q1.a + q2.a, q1.b + q2.b, q1.c + q2.c, q1.d + q2.d)
end

-- Overloading the multiplication operator
function Quaternion.__mul(q1, q2)
    local a = q1.a * q2.a - q1.b * q2.b - q1.c * q2.c - q1.d * q2.d
    local b = q1.a * q2.b + q1.b * q2.a + q1.c * q2.d - q1.d * q2.c
    local c = q1.a * q2.c - q1.b * q2.d + q1.c * q2.a + q1.d * q2.b
    local d = q1.a * q2.d + q1.b * q2.c - q1.c * q2.b + q1.d * q2.a
    return Quaternion.new(a, b, c, d)
end

function Quaternion.__eq(q1, q2)
    return q1.a == q2.a and q1.b == q2.b and q1.c == q2.c and q1.d == q2.d
end

function Quaternion:conjugate()
    return Quaternion.new(self.a, -self.b, -self.c, -self.d)
end

function Quaternion:coefficients()
    return self.a, self.b, self.c, self.d
end

function Quaternion:__tostring()
    local result = tostring(self.a)
    if self.b ~= 0 then result = result .. (self.b > 0 and "+" or "") .. self.b .. "i" end
    if self.c ~= 0 then result = result .. (self.c > 0 and "+" or "") .. self.c .. "j" end
    if self.d ~= 0 then result = result .. (self.d > 0 and "+" or "") .. self.d .. "k" end
    return result
end


