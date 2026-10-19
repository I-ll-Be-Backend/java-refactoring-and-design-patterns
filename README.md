# Java Refactoring and Design Patterns

Code from the [I'll Be Backend](https://www.youtube.com/@illbebackend) videos: messy Java code gets better, one small, safe refactoring at a time. Every lesson has a video, an [interactive version](https://illbebackend.dev) you can step through, and a folder here with the code after every step.

## Code smells

| Lesson | Steps | Video | Interactive |
|---|---|---|---|
| [Long parameter list: introduce a parameter object](long-parameter-list-introduce-a-parameter-object/) | 11 | [Watch](https://www.youtube.com/watch?v=L2ShPvhDfj4) | [Open](https://illbebackend.dev/lessons/long-parameter-list-introduce-a-parameter-object/java/) |
| [Clean code: replace comments with code](replace-comments-with-code/) | 13 | [Watch](https://www.youtube.com/watch?v=RlgHo7pCN4M) | [Open](https://illbebackend.dev/lessons/replace-comments-with-code/java/) |

## Principles

| Lesson | Steps | Video | Interactive |
|---|---|---|---|
| [DRY: one rule, one place](dry/) | 11 | [Watch](https://www.youtube.com/watch?v=UtnqwwrqMjU) | [Open](https://illbebackend.dev/lessons/dry/java/) |
| [Clean code: extract until it reads](extract-until-it-reads/) | 10 | [Watch](https://www.youtube.com/watch?v=Mlq_qHkKYTo) | [Open](https://illbebackend.dev/lessons/extract-until-it-reads/java/) |
| [KISS: replace a rule engine with plain code](kiss-unwind-a-clever-abstraction/) | 13 | [Watch](https://www.youtube.com/watch?v=UGaIVg45Pto) | [Open](https://illbebackend.dev/lessons/kiss-unwind-a-clever-abstraction/java/) |
| [Clean code: rename for intent](rename-for-intent/) | 13 | [Watch](https://www.youtube.com/watch?v=RXJt2qePBKQ) | [Open](https://illbebackend.dev/lessons/rename-for-intent/java/) |
| [YAGNI: delete speculative options](yagni-delete-speculative-options/) | 11 | [Watch](https://www.youtube.com/watch?v=jPjEHSp6YWI) | [Open](https://illbebackend.dev/lessons/yagni-delete-speculative-options/java/) |

## How to use it

- Open a lesson folder and read its README, then the step folders in order.
- `sh check.sh` compiles every step in Docker with `eclipse-temurin:21-jdk`, the same check every lesson passed before its video went out.
- Found a bug in a lesson? Open an issue.

## License

The code is [MIT No Attribution](LICENSE): use it however you like. The videos, the name and the logo are covered by the [terms](https://illbebackend.dev/terms/).
