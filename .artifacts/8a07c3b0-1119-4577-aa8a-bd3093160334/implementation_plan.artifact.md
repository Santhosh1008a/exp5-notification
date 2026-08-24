# Implementation Plan - BookNest Visual & Data Evolution (v2)

This plan focuses on making the "Continue Reading" section dynamic, upgrading the UI of both Login and Home pages with advanced modern design patterns, and refining the tone by removing emojis for a more sophisticated feel.

## User Review Required

> [!IMPORTANT]
> I am introducing multiple design styles: **Liquid Glass**, **Claymorphism**, **Neomorphism**, and **Spatial UI** to achieve a "High-Aesthetic" look.
> **Emojis will be removed** from the text and replaced with premium vector icons where appropriate.
> The "Continue Reading" section will now show a horizontal scrolling carousel of your active books.

## Proposed Changes

### UI & UX Upgrades

#### 🎨 Design Patterns to be Applied:
- **Liquid Glass**: High-gloss translucent layers for cards.
- **Claymorphism**: Soft 3D rounded shapes for genre chips and progress bars.
- **Neomorphism**: Soft shadow depth for login fields and buttons.
- **Spatial UI**: Layered z-index depth for a "hovering" feel.

#### [MODIFY] [activity_login.xml](file:///C:/Users/santh/AndroidStudioProjects/exp5notification/app/src/res/layout/activity_login.xml)
- Apply **Neomorphic** input fields (soft inset shadows).
- Apply **Liquid Glass** to the login container.
- Replace the logo with a more sophisticated vector graphic.

#### [MODIFY] [fragment_home.xml](file:///C:/Users/santh/AndroidStudioProjects/exp5notification/app/src/res/layout/fragment_home.xml)
- Remove emojis from "Good evening" and "Explore Genres".
- Replace the static "Continue Reading" card with a horizontal `RecyclerView`.
- Update genre icons to be **Claymorphic** 3D-styled icons (using custom drawables).

#### [MODIFY] [item_genre.xml](file:///C:/Users/santh/AndroidStudioProjects/exp5notification/app/src/res/layout/item_genre.xml)
- Remove emojis from genre cards.
- Replace with elegant, minimal vector icons.

### Data & Logic

#### [MODIFY] [BookRepository.kt](file:///C:/Users/santh/AndroidStudioProjects/exp5notification/app/src/main/java/com/example/exp5notification/data/BookRepository.kt)
- Update multiple books to have varying `progress` values (e.g., 20%, 45%, 80%).

#### [MODIFY] [HomeFragment.kt](file:///C:/Users/santh/AndroidStudioProjects/exp5notification/app/src/main/java/com/example/exp5notification/ui/HomeFragment.kt)
- Remove emoji concatenations in code.
- Implement horizontal adapter for "Continue Reading" carousel.

## Verification Plan

### Manual Verification
1. **Login Page Audit**: Verify the Neomorphic and Liquid Glass styles.
2. **Dynamic Section**: Verify multiple books appear in the horizontal "Continue Reading" list.
3. **Emoji Check**: Ensure no emojis remain on any screen.
4. **Visual Audit**: Verify the "Spatial" feel and consistency between Login and Home.
