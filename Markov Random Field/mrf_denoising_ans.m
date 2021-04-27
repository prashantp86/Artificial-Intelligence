%------------------------------------------------------------
%
% Author : Pratik Shah
% Date : March 2, 2020
% Demo exercise: CS308 Introduction to AI
%
% (Conditional) Markov Random Field for Image Denoising
%
%-------------------------------------------------------------

clear all;
close all;
Image = imread("cameraman.tif");
[M,N] = size(Image);

Image=im2double(Image)+randn(size(Image))*0.1;
new_image=Image;

max_iter = 1000;
lamda = 5;
mu = .1;

for epochs=1:max_iter:
    E1 = sum((new_image-Image).^2);
    E2 = sum(sum(diff(new_image,11).^2))+sum(sum(diff(new_image,1,2).^2));
    d1 = new_image-Image; 
    s1 = sqrt(10);
    d2 = 2*new_image-circshift(new_image,-1,1)-circshift(new_image,-1,2); 
    s2 = sqrt(10);
    gradient = 2*d1./(d1.^2+2*s1^2) + lamda*2*d2./(d2.^2+2*s2^2);
    new_image = new_image - mu*gradient;
end

figure(1);
subplot(1,2,1);imshow(Image);
subplot(1,2,2);imshow(new_image);
