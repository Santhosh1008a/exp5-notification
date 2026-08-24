# Task List - BookNest Visual & Data Overhaul

## Design Assets & Drawables
- [ ] Create `bg_liquid_glass_card.xml` (High-gloss glass).
- [ ] Create `bg_neomorph_input.xml` (Soft inset shadows).
- [ ] Create `bg_neomorph_button.xml` (Soft elevation).
- [ ] Create `bg_clay_progress.xml` (Soft 3D progress bar).
- [ ] Create vector icons for genres to replace emojis.

## Data Layer
- [ ] Update `BookRepository.kt` with multiple "in-progress" books.

## Login Page Overhaul
- [ ] Modify `activity_login.xml` with Liquid Glass and Neomorphism.
- [ ] Remove emojis from `LoginActivity.kt` and layout.

## Home Page Evolution
- [ ] Modify `fragment_home.xml`:
    - [ ] Add horizontal RecyclerView for "Continue Reading".
    - [ ] Remove all emojis.
- [ ] Create `item_continue_reading.xml` with Spatial UI depth.
- [ ] Update `item_genre.xml` (Remove emojis, add vector icons).
- [ ] Update `HomeFragment.kt`:
    - [ ] Implement `ContinueReadingAdapter`.
    - [ ] Update logic for horizontal carousel.

## Final Refinement
- [ ] Verify animations and spatial layering.
- [ ] Perform final build and verify notifications.
- [ ] Update `walkthrough.artifact.md`.
