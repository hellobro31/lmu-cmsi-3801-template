import Foundation

struct NegativeAmountError: Error {}
struct NoSuchFileError: Error {}

func change(_ amount: Int) -> Result<[Int:Int], NegativeAmountError> {
    if amount < 0 {
        return .failure(NegativeAmountError())
    }
    var (counts, remaining) = ([Int:Int](), amount)
    for denomination in [25, 10, 5, 1] {
        (counts[denomination], remaining) = 
            remaining.quotientAndRemainder(dividingBy: denomination)
    }
    return .success(counts)
}

// Write your first then lower case function here
func findFirstLowercase(for array: [String], satisfying predicate: (String) -> Bool) -> String? {
    return array.first(where: predicate)?.lowercased()
}

// Write your say function here
class Say {
    private var phraseBuilder: String

    
    init(_ word: String) {
        self.phraseBuilder = word
    }

    func and(_ word: String) -> Say {
        if !phraseBuilder.isEmpty || word.isEmpty {
            phraseBuilder += " "
        }
        phraseBuilder += word
        return self
    }

    var phrase: String {
        return phraseBuilder
    }

    // Method to clone the current state (to prevent shared states)
    func clone() -> Say {
        return Say(self.phraseBuilder)
    }
}

// Function to start the chain
func say(_ words: String...) -> Say {
    if words.isEmpty {
        return Say("")
    } else {
        return Say(words[0])
    }
}
// Write your meaningfulLineCount function here
import Foundation

enum FileError: Error {
    case fileNotFound(String)
}

func countValidLines(inFile fileName: String) async -> Result<Int, Error> {
    do {
        let fileURL = URL(fileURLWithPath: fileName)
        let fileContent = try await String(contentsOf: fileURL)

        let lineCount = fileContent
            .split(whereSeparator: \.isNewline)  // Split file content into lines
            .filter { !$0.trimmingCharacters(in: .whitespaces).isEmpty }  // Ignore empty or whitespace-only lines
            .filter { !$0.trimmingCharacters(in: .whitespaces).hasPrefix("#") }  // Ignore lines starting with #
            .count
        
        return .success(lineCount)
    } catch {
        return .failure(FileError.fileNotFound("File \(fileName) not found or unreadable."))
    }
}

@main
struct Main {
    static func main() async {
        let result = await countValidLines(inFile: "example.txt")
        switch result {
        case .success(let count):
            print("Valid line count: \(count)")
        case .failure(let error):
            print("Error: \(error)")
        }
    }
}

// Write your Quaternion struct here

// Write your Binary Search Tree enum here
