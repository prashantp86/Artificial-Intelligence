
clear all;
close all;

%y=csvread("two_bent_coins.csv");
y=csvread("2020_ten_bent_coins.csv");

% True Parameters
% theta = [.2 .5];

theta = [.2 .5 .1 .3 .4 .6 .7 .8 .9 .2];

h = sum(y,2); t = 100-h;
th(1,:) = theta;

for n=2:500
 LH=zeros(size(h));
 for k=1:10
  lh(:,k) = (th(n-1,k).^h).*((1-th(n-1,k)).^t);
  LH=LH+lh(:,k);
 end
 for k=1:10
  th(n,k)=sum(h.*lh(:,k)./LH)/sum((h+t).*lh(:,k)./LH);
 end
end

