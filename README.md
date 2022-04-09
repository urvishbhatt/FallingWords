# Falling Words

Test your Spanish vocabulary with Falling Words App. To win the game, you have to score 5 points. If the translation is right, then press the `CORRECT` button and if the translation is wrong, press the `WRONG` button. You will have 7 seconds to select the option. You have three lives($). If you do not select/select wrong, then lives will be deducted.

## HOW MUCH TIME IS INVESTED

- A total of 4 hours and 30 - 45 minutes are invested in the task.

## HOW TIME IS DISTRIBUTED
> First hour
- Understand the requirement, especially how Game logic works with other android Components like LiveData, ViewModel, and Animation.
- Made Architecture on Paper. I used MVVM since it makes code more testable, Easy to the stature, and helpful in data binding.
- Find opensource Mobile UX/UI. convert them into vectors with the help of Figma Software.
- Create empty classes like Activity, Fragment, and ViewModel.

> Second hour
- Implement UI design
- Developed Networking work with help of Retrofit, Moshi & Coroutines.

> Third & Fourth hour
- Implement actual game logic here(ex.
getWords, nextWord, playerResponse). At this point, Game was working properly without animation.
- Implement Animation and LiveData. I stuck here badly. App was leaking memory, but eventually solved the problem.

> Last 30 - 45 Minutes
- Solved more bugs, Add useful logs and comments
- Wrote Unit Tests
- Create Document final document

## DECISIONS MADE FOR SOLVING CERTAIN ASPECTS OF GAME
- Create Constants file which contains `GAME_API`,  `TOTAL_QUESTIONS`, `TOTAL_RETRY`, and `TIME_PER_WORD`. those are the main feature of the game, and they can be easily changeable.
- when words are in fetch in the List. we create another sublist called `totalquestion`. size of `totalquations = total points to win + total lives - 1`. 
-  In `nextWord()` we generated random number 0 or 1. if 0 then translation pair is correct. if 1 then the translation pair is wrong. that's how the chances of right and wrong translation are 50% each.
-  Wrote Game logic in `ViewModel` itself since logic is straightforward. if it is more complex I definitely choose a separate file and take dagger in the discussion.
-  Fragment can only access `playerResponse(), retryString(), scoreString()` and TextView will be updated with the help of LiveData & Observers.
-  Starting and Stopping animation depends on the player's score.
-  Make sure Binding, animation & Corotines Job is shut down properly once the game is finished.

## DECISION MADE DUE TO RESTRICTION OF TIME
- Wrote Game logic in ViewModel. Not best practice but does the job. definitely add in separate folder. that's how it becomes easy to scale, maintain the codebase, unite test, and Dependency injection.
- Only one Test case. I wrote the first test case after 4 hours. if I had more time I could write more.
- The player can not choose difficulty or the number of questions. it requires a little more work but is definitely doable.
- Dimensions are not in the resource folder.
- Restricted by portrait mode only.
- Cannot restart the match only you move to ResultFragment, restricted by `Navigation`.

## IMPROVEMENT
- Make Game logic separate from ViewModel.
- Add more Test cases. especially integration test.  
- End of Animation at bottom of the screen. currently, textview travels the same as the height of the screen.
