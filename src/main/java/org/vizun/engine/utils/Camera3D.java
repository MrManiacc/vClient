package org.vizun.engine.utils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Camera3D implements Camera {
    private float x = 0.0F;
    private float y = 0.0F;
    private float z = 0.0F;
    private float pitch = 0.0F;
    private float yaw = 0.0F;
    private float roll = 0.0F;
    private float aspectRatio = 1.0F;
    private float fov;
    public final float zNear;
    public final float zFar;

    public Camera3D() {
        this.zNear = 0.3F;
        this.zFar = 100.0F;
    }

    public Camera3D(float aspectRatio) {
        if (aspectRatio <= 0.0F) {
            throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
        }
        this.aspectRatio = aspectRatio;
        this.zNear = 0.3F;
        this.zFar = 100.0F;
    }

    public Camera3D(CameraBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.pitch = builder.pitch;
        this.yaw = builder.yaw;
        this.roll = builder.roll;
        this.aspectRatio = builder.aspectRatio;
        this.zNear = builder.zNear;
        this.zFar = builder.zFar;
        this.fov = builder.fov;
    }

    public Camera3D(float aspectRatio, float x, float y, float z) {
        this(aspectRatio);
    }


    public Camera3D(float aspectRatio, float x, float y, float z, float pitch, float yaw, float roll) {
        this(aspectRatio, x, y, z);
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public static class CameraBuilder {
        private float x = 0.0F;
        private float y = 0.0F;
        private float z = 0.0F;
        private float pitch = 0.0F;
        private float yaw = 0.0F;
        private float roll = 0.0F;
        private float aspectRatio = 1.0F;
        private float zNear = 0.001F;
        private float zFar = 100.0F;
        private float fov = 90.0F;

        public CameraBuilder setAspectRatio(float aspectRatio) {
            if (aspectRatio <= 0.0F) {
                throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
            }
            this.aspectRatio = aspectRatio;
            return this;
        }

        public CameraBuilder setNearClippingPane(float nearClippingPane) {
            if (nearClippingPane <= 0.0F) {
                throw new IllegalArgumentException("nearClippingPane " + nearClippingPane + " is 0 or less");
            }
            this.zNear = nearClippingPane;
            return this;
        }

        public CameraBuilder setFarClippingPane(float farClippingPane) {
            if (farClippingPane <= 0.0F) {
                throw new IllegalArgumentException("farClippingPane " + farClippingPane + " is 0 or less");
            }
            this.zFar = farClippingPane;
            return this;
        }


        public CameraBuilder setFieldOfView(float fov) {
            this.fov = fov;
            return this;
        }


        public CameraBuilder setPosition(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }


        public CameraBuilder setRotation(float pitch, float yaw, float roll) {
            this.pitch = pitch;
            this.yaw = yaw;
            this.roll = roll;
            return this;
        }


        public Camera3D build() {
            if (this.zFar <= this.zNear) {
                throw new IllegalArgumentException("farClippingPane " + this.zFar + " is the same or less than " + "nearClippingPane " + this.zNear);
            }
            return new Camera3D(this);
        }
    }


    public void updateMouse() {
        float MAX_UP = 90.0F;
        float MAX_DOWN = -90.0F;
        float mouseDX = Mouse.getDX() * 0.16F;
        float mouseDY = Mouse.getDY() * 0.16F;

        if (this.yaw + mouseDX >= 360.0F) {
            this.yaw = (this.yaw + mouseDX - 360.0F);
        } else if (this.yaw + mouseDX < 0.0F) {
            this.yaw = (360.0F - this.yaw + mouseDX);
        } else {
            this.yaw += mouseDX;
        }
        if ((this.pitch - mouseDY >= -90.0F) && (this.pitch - mouseDY <= 90.0F)) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < -90.0F) {
            this.pitch = -90.0F;
        } else if (this.pitch - mouseDY > 90.0F) {
            this.pitch = 90.0F;
        }
    }

    public void updateMouse(float mouseSpeed) {
        float MAX_UP = 90.0F;
        float MAX_DOWN = -90.0F;
        float mouseDX = Mouse.getDX() * mouseSpeed * 0.16F;
        float mouseDY = Mouse.getDY() * mouseSpeed * 0.16F;

        if (this.yaw + mouseDX >= 360.0F) {
            this.yaw = (this.yaw + mouseDX - 360.0F);
        } else if (this.yaw + mouseDX < 0.0F) {
            this.yaw = (360.0F - this.yaw + mouseDX);
        } else {
            this.yaw += mouseDX;
        }
        if ((this.pitch - mouseDY >= -90.0F) && (this.pitch - mouseDY <= 90.0F)) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < -90.0F) {
            this.pitch = -90.0F;
        } else if (this.pitch - mouseDY > 90.0F) {
            this.pitch = 90.0F;
        }
    }

    public void updateMouse(float mouseSpeed, float maxUp, float maxDown) {
        float mouseDX = Mouse.getDX() * mouseSpeed * 0.16F;
        float mouseDY = Mouse.getDY() * mouseSpeed * 0.16F;

        if (this.yaw + mouseDX >= 360.0F) {
            this.yaw = (this.yaw + mouseDX - 360.0F);
        } else if (this.yaw + mouseDX < 0.0F) {
            this.yaw = (360.0F - this.yaw + mouseDX);
        } else {
            this.yaw += mouseDX;
        }
        if ((this.pitch - mouseDY >= maxDown) && (this.pitch - mouseDY <= maxUp)) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < maxDown) {
            this.pitch = maxDown;
        } else if (this.pitch - mouseDY > maxUp) {
            this.pitch = maxUp;
        }
    }

    public void updateKeys(float delta) {
        if (delta <= 0.0F) {
            throw new IllegalArgumentException("Delta must be greater than 0");
        }
        boolean keyUp = Keyboard.isKeyDown(17);
        boolean keyDown = Keyboard.isKeyDown(31);
        boolean keyLeft = Keyboard.isKeyDown(30);
        boolean keyRight = Keyboard.isKeyDown(32);
        boolean space = Keyboard.isKeyDown(57);
        boolean shift = Keyboard.isKeyDown(42);

        if ((keyUp) && (keyRight) && (!keyLeft) && (!keyDown)) {
            moveLookDir(delta * 0.003F, 0.0F, -delta * 0.003F);
        }
        if ((keyUp) && (keyLeft) && (!keyRight) && (!keyDown)) {
            moveLookDir(-delta * 0.003F, 0.0F, -delta * 0.003F);
        }
        if ((keyUp) && (!keyLeft) && (!keyRight) && (!keyDown)) {
            moveLookDir(0.0F, 0.0F, -delta * 0.003F);
        }
        if ((keyDown) && (keyLeft) && (!keyRight) && (!keyUp)) {
            moveLookDir(-delta * 0.003F, 0.0F, delta * 0.003F);
        }
        if ((keyDown) && (keyRight) && (!keyLeft) && (!keyUp)) {
            moveLookDir(delta * 0.003F, 0.0F, delta * 0.003F);
        }
        if ((keyDown) && (!keyUp) && (!keyLeft) && (!keyRight)) {
            moveLookDir(0.0F, 0.0F, delta * 0.003F);
        }
        if ((keyLeft) && (!keyRight) && (!keyUp) && (!keyDown)) {
            moveLookDir(-delta * 0.003F, 0.0F, 0.0F);
        }
        if ((keyRight) && (!keyLeft) && (!keyUp) && (!keyDown)) {
            moveLookDir(delta * 0.003F, 0.0F, 0.0F);
        }
        if ((space) && (!shift)) {
            this.y += delta * 0.003F;
        }
        if ((shift) && (!space)) {
            this.y -= delta * 0.003F;
        }
    }

    public void updateKeys(float delta, float speed) {
        if (delta <= 0.0F) {
            throw new IllegalArgumentException("Delta must be greater than 0");
        }
        boolean keyUp = Keyboard.isKeyDown(17);
        boolean keyDown = Keyboard.isKeyDown(31);
        boolean keyLeft = Keyboard.isKeyDown(30);
        boolean keyRight = Keyboard.isKeyDown(32);
        boolean space = Keyboard.isKeyDown(57);
        boolean shift = Keyboard.isKeyDown(42);

        if ((keyUp) && (keyRight) && (!keyLeft) && (!keyDown)) {
            moveLookDir(speed * delta * 0.003F, 0.0F, -speed * delta * 0.003F);
        }
        if ((keyUp) && (keyLeft) && (!keyRight) && (!keyDown)) {
            moveLookDir(-speed * delta * 0.003F, 0.0F, -speed * delta * 0.003F);
        }
        if ((keyUp) && (!keyLeft) && (!keyRight) && (!keyDown)) {
            moveLookDir(0.0F, 0.0F, -speed * delta * 0.003F);
        }
        if ((keyDown) && (keyLeft) && (!keyRight) && (!keyUp)) {
            moveLookDir(-speed * delta * 0.003F, 0.0F, speed * delta * 0.003F);
        }
        if ((keyDown) && (keyRight) && (!keyLeft) && (!keyUp)) {
            moveLookDir(speed * delta * 0.003F, 0.0F, speed * delta * 0.003F);
        }
        if ((keyDown) && (!keyUp) && (!keyLeft) && (!keyRight)) {
            moveLookDir(0.0F, 0.0F, speed * delta * 0.003F);
        }
        if ((keyLeft) && (!keyRight) && (!keyUp) && (!keyDown)) {
            moveLookDir(-speed * delta * 0.003F, 0.0F, 0.0F);
        }
        if ((keyRight) && (!keyLeft) && (!keyUp) && (!keyDown)) {
            moveLookDir(speed * delta * 0.003F, 0.0F, 0.0F);
        }
        if ((space) && (!shift)) {
            this.y += speed * delta * 0.003F;
        }
        if ((shift) && (!space)) {
            this.y -= speed * delta * 0.003F;
        }
    }


    public void updateKeys(float delta, float speedX, float speedY, float speedZ) {
    }


    public void moveLookDir(float dx, float dy, float dz) {
        this.z = ((float) (this.z + (dx * (float) Math.cos(Math.toRadians(this.yaw - 90.0F)) + dz * Math.cos(Math.toRadians(this.yaw)))));
        this.x = ((float) (this.x - (dx * (float) Math.sin(Math.toRadians(this.yaw - 90.0F)) + dz * Math.sin(Math.toRadians(this.yaw)))));
        this.y = ((float) (this.y + (dy * (float) Math.sin(Math.toRadians(this.pitch - 90.0F)) + dz * Math.sin(Math.toRadians(this.pitch)))));
    }

    public void setPos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setFOV(float fov) {
        this.fov = fov;
    }

    public void setAspectRation(float ratio) {
        this.aspectRatio = ratio;
    }

    public void applyOrtho() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(-this.aspectRatio, this.aspectRatio, -1.0D, 1.0D, 0.0D, this.zFar);
        GL11.glPopAttrib();
    }

    public void applyProjection() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GLU.gluPerspective(this.fov, this.aspectRatio, this.zNear, this.zFar);
        GL11.glPopAttrib();
    }

    public void applyTranslations() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5888);
        GL11.glRotatef(this.pitch, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.yaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.roll, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-this.x, -this.y, -this.z);
        GL11.glPopAttrib();
    }

    public void setRotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getRoll() {
        return this.roll;
    }

    public float getFOV() {
        return this.fov;
    }

    public float getAspectRatio() {
        return this.aspectRatio;
    }

    public float getNearClippingPlane() {
        return this.zNear;
    }

    public float getFarClippingPlane() {
        return this.zFar;
    }
}
